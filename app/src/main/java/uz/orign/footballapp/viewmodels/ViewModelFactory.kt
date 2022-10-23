package uz.orign.footballapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.orign.footballapp.repositories.FBRepository
import uz.orign.footballapp.utils.network.NetworkHelper
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val fbRepository: FBRepository,
    private val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(fbRepository, networkHelper) as T
        }
        throw IllegalArgumentException("Error")
    }
}