package uz.orign.footballapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import uz.orign.footballapp.adapters.LeagueAdapter
import uz.orign.footballapp.database.AppDatabase
import uz.orign.footballapp.databinding.ActivityMainBinding
import uz.orign.footballapp.network.ApiClient
import uz.orign.footballapp.repositories.FBRepository
import uz.orign.footballapp.utils.network.NetworkHelper
import uz.orign.footballapp.utils.network.Status
import uz.orign.footballapp.viewmodels.MainViewModel
import uz.orign.footballapp.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var logo = ""
    private var name = ""


    private var leagueAdapter = LeagueAdapter {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("logo", logo)
        intent.putExtra("name", name)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fbRepository = FBRepository(ApiClient.apiService, AppDatabase.getInstance(this))
        val networkHelper = NetworkHelper(this)

        mainViewModel = ViewModelProvider(this, ViewModelFactory(fbRepository, networkHelper))[MainViewModel::class.java]

       var n = mainViewModel.liveStanding.value?.message
        Log.e("TAG", "onCreate:$n ")

        mainViewModel.getStanding().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    leagueAdapter.submitList(it.data?.response)
                    binding.rvLeague.adapter = leagueAdapter
                    logo = it.data?.response?.get(0)?.league?.logo.toString()
                    name = it.data?.response?.get(0)?.league?.name.toString()
                }

                Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    binding.progressbar.visibility = View.GONE
                    Log.d("Error", it.message.toString())
                }

            }
        }

    }
}