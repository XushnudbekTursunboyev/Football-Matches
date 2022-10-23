package uz.orign.footballapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.orign.footballapp.network.model.standing_league.MyStandings
import uz.orign.footballapp.network.model.standing_team.MyStandingTeam
import uz.orign.footballapp.repositories.FBRepository
import uz.orign.footballapp.utils.network.NetworkHelper
import uz.orign.footballapp.utils.network.Resource

class MainViewModel(
    private val fbRepository: FBRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

     val liveStanding = MutableLiveData<Resource<MyStandings>>()
     val liveStandingTeam = MutableLiveData<Resource<MyStandingTeam>>()

    init {
        fetchLeagues()
    }

    private fun fetchLeagues() {
        viewModelScope.launch {
            liveStanding.postValue(Resource.loading(null))
            liveStandingTeam.postValue(Resource.loading(null))


            if (networkHelper.isNetworkConnected()) {
                val remoteStanding = fbRepository.getStandingList()
                val remoteStandingTeam = fbRepository.getStandingTeamList()
                if (remoteStanding.isSuccessful) liveStanding.value = Resource.success(remoteStanding.body()) else liveStanding.postValue(Resource.error(remoteStanding.errorBody()?.string() ?: "", null))
                if (remoteStandingTeam.isSuccessful) liveStandingTeam.value = Resource.success(remoteStandingTeam.body()) else liveStandingTeam.postValue(Resource.error(remoteStandingTeam.errorBody()?.string() ?: "", null))
            } else {
               // liveData.postValue(Resource.success(fbRepository.getLocalLeagues()))
            }
        }
    }

 fun getStanding(): LiveData<Resource<MyStandings>> = liveStanding
 fun getStandingTeam(): LiveData<Resource<MyStandingTeam>> = liveStandingTeam
}