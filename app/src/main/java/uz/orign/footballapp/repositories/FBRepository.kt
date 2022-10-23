package uz.orign.footballapp.repositories

import uz.orign.footballapp.database.AppDatabase
import uz.orign.footballapp.network.ApiService
import uz.orign.footballapp.utils.CONSTANTS.API_KEY

class FBRepository(private val apiService: ApiService, private val appDatabase: AppDatabase) {

    suspend fun getStandingList() = apiService.getStanding()
    suspend fun getStandingTeamList() = apiService.getStandingTeam()

  //  suspend fun getLocalLeagues() = appDatabase.fbDao().getAllLeagues()

  //  suspend fun addLeagues(list: List<Match>) = appDatabase.fbDao().addLeague(list)
}