package uz.orign.footballapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.orign.footballapp.R
import uz.orign.footballapp.adapters.TableAdapterAway
import uz.orign.footballapp.adapters.TableAdapterHome
import uz.orign.footballapp.adapters.TableAdapterNew
import uz.orign.footballapp.database.AppDatabase
import uz.orign.footballapp.databinding.FragmentTableBinding
import uz.orign.footballapp.databinding.TeamItemBinding
import uz.orign.footballapp.network.ApiClient
import uz.orign.footballapp.network.model.standing_league.Standing
import uz.orign.footballapp.repositories.FBRepository
import uz.orign.footballapp.utils.network.NetworkHelper
import uz.orign.footballapp.utils.network.Status
import uz.orign.footballapp.viewmodels.MainViewModel
import uz.orign.footballapp.viewmodels.ViewModelFactory

class TableFragment : Fragment(){

    private lateinit var _bn: FragmentTableBinding
    private val bn get() = _bn
    private lateinit var mainViewModel: MainViewModel
    private var selectItem = false
    private var selectItemPos = 0

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bn = FragmentTableBinding.inflate(inflater, container, false)

        val fbRepository = FBRepository(ApiClient.apiService, AppDatabase.getInstance(requireActivity()))
        val networkHelper = NetworkHelper(requireActivity())

        mainViewModel = ViewModelProvider(requireActivity(), ViewModelFactory(fbRepository, networkHelper))[MainViewModel::class.java]

        mainViewModel.getStanding().observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    bn.progressbar.visibility = View.GONE
                    var tableAdapterNew = TableAdapterNew()

                    tableAdapterNew.context = requireActivity()
                    tableAdapterNew.list = it.data?.response?.get(0)?.league?.standings?.get(0)!!
                    tableAdapterNew.isTask = true
                    tableAdapterNew.onpress = object : TableAdapterNew.onPress {
                        override fun selected(position: Int, oldItem: Int, list: ArrayList<TeamItemBinding>, itemview: TeamItemBinding, d: Standing) {
                            selectItem = true
                            selectItemPos = position
                        }
                        override fun click(d: Standing) {

                        }
                    }
                    bn.rvTable.adapter = tableAdapterNew
                    tableAdapterNew.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    bn.progressbar.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    Log.d("Error", it.message.toString())
                }
            }
        }

        bn.apply {
            btnAll.setOnClickListener {
                btnAll.setBackgroundResource(R.drawable.linear_select)
                btnHome.setBackgroundResource(R.drawable.linear_back)
                btnAway.setBackgroundResource(R.drawable.linear_back)
                loadAll()
            }

            btnHome.setOnClickListener {
                btnAll.setBackgroundResource(R.drawable.linear_back)
                btnHome.setBackgroundResource(R.drawable.linear_select)
                btnAway.setBackgroundResource(R.drawable.linear_back)
                loadHome()
            }

            btnAway.setOnClickListener {
                btnAll.setBackgroundResource(R.drawable.linear_back)
                btnHome.setBackgroundResource(R.drawable.linear_back)
                btnAway.setBackgroundResource(R.drawable.linear_select)
                loadAway()
            }

        }

        return bn.root
    }

    private fun loadAll(){
        mainViewModel.getStanding().observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    bn.progressbar.visibility = View.GONE
                    var tableAdapterNew = TableAdapterNew()

                    tableAdapterNew.context = requireActivity()
                    tableAdapterNew.list = it.data?.response?.get(0)?.league?.standings?.get(0)!!
                    tableAdapterNew.isTask = true
                    tableAdapterNew.onpress = object : TableAdapterNew.onPress {
                        override fun selected(position: Int, oldItem: Int, list: ArrayList<TeamItemBinding>, itemview: TeamItemBinding, d: Standing) {
                            selectItem = true
                            selectItemPos = position
                        }
                        override fun click(d: Standing) {

                        }
                    }
                    bn.rvTable.adapter = tableAdapterNew
                    tableAdapterNew.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    bn.progressbar.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    Log.d("Error", it.message.toString())
                }
            }
        }
    }

    private fun loadHome(){
        mainViewModel.getStanding().observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    bn.progressbar.visibility = View.GONE
                    var tableAdapterNew = TableAdapterHome()

                    tableAdapterNew.context = requireActivity()
                    tableAdapterNew.list = it.data?.response?.get(0)?.league?.standings?.get(0)!!
                    tableAdapterNew.isTask = true
                    tableAdapterNew.onpress = object : TableAdapterHome.onPress {
                        override fun selected(position: Int, oldItem: Int, list: ArrayList<TeamItemBinding>, itemview: TeamItemBinding, d: Standing) {
                            selectItem = true
                            selectItemPos = position
                        }
                        override fun click(d: Standing) {

                        }
                    }
                    bn.rvTable.adapter = tableAdapterNew
                    tableAdapterNew.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    bn.progressbar.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    Log.d("Error", it.message.toString())
                }
            }
        }
    }

    private fun loadAway(){
        mainViewModel.getStanding().observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    bn.progressbar.visibility = View.GONE
                    var tableAdapterNew = TableAdapterAway()

                    tableAdapterNew.context = requireActivity()
                    tableAdapterNew.list = it.data?.response?.get(0)?.league?.standings?.get(0)!!
                    tableAdapterNew.isTask = true
                    tableAdapterNew.onpress = object : TableAdapterAway.onPress {
                        override fun selected(position: Int, oldItem: Int, list: ArrayList<TeamItemBinding>, itemview: TeamItemBinding, d: Standing) {
                            selectItem = true
                            selectItemPos = position
                        }
                        override fun click(d: Standing) {

                        }
                    }
                    bn.rvTable.adapter = tableAdapterNew
                    tableAdapterNew.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    bn.progressbar.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    Log.d("Error", it.message.toString())
                }
            }
        }
    }

}