package uz.orign.footballapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import uz.orign.footballapp.adapters.ViewPagerAdapter
import uz.orign.footballapp.databinding.ActivityMain2Binding
import uz.orign.footballapp.ui.MatchesFragment
import uz.orign.footballapp.ui.TableFragment

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private val fList = listOf(MatchesFragment(), TableFragment())
    private val tList = listOf("Matches", "Table")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val logo = intent.getStringExtra("logo")
        val name = intent.getStringExtra("name")

        Glide.with(this).load(logo).centerCrop().placeholder(R.drawable
            .ic_launcher_foreground).into(binding.imLeagueFlag)
        binding.tvLeagueName.text = name

        binding.back.setOnClickListener {
            finish()
        }

        tabLayout()
    }

    private fun tabLayout() {
        val adapter = ViewPagerAdapter( this, fList)
        binding.vp.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.vp) { t, p ->
            t.text = tList[p]
        }.attach()
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(1))

    }
}