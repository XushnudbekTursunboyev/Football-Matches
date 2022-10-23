package uz.orign.footballapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.orign.footballapp.R
import uz.orign.footballapp.databinding.LeagueItemBinding
import uz.orign.footballapp.network.model.standing_league.Response

/**
 *Created by Xushnudbek Tursunboyev on 09/10/2022
 */

class LeagueAdapter(val onClick: (model: Response) -> Unit) : ListAdapter<Response, LeagueAdapter.ViewHolder>(ITEM_DIFF) {

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Response>() {
            override fun areItemsTheSame(oldItem: Response, newItem: Response) =
                oldItem.league.id == newItem.league.id

            override fun areContentsTheSame(oldItem: Response, newItem: Response): Boolean{
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LeagueItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ViewHolder(private val binding: LeagueItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun bind(d: Response) {
            binding.apply {
                root.setOnClickListener {
                    onClick.invoke(d)

                }
                binding.apply {
                    tvLeagueName.text = d.league.name
                    tvCountryName.text = d.league.country
                    Glide.with(itemView.context).load( d.league.logo).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(imLeague)

                    //first team
                    tvFirstTeamName.text = d.league.standings.get(0).get(0).team.name
                    tvFirstTeamDraw.text = d.league.standings.get(0).get(0).all.draw.toString()
                    tvFirstTeamLost.text = d.league.standings.get(0).get(0).all.lose.toString()
                    tvFirstTeamGa.text = d.league.standings.get(0).get(0).all.goals.against.toString()
                    tvFirstTeamGd.text = d.league.standings.get(0).get(0).goalsDiff.toString()
                    tvFirstTeamPts.text = d.league.standings.get(0).get(0).points.toString()
                    Glide.with(itemView.context).load( d.league.standings.get(0).get(0).team.logo).centerCrop().placeholder(R.drawable
                        .ic_launcher_foreground).into(imFirstTeamIcon)

                    //second team
                    tvSecondTeamName.text = d.league.standings.get(0).get(1).team.name
                    tvSecondTeamDraw.text = d.league.standings.get(0).get(1).all.draw.toString()
                    tvSecondTeamLost.text = d.league.standings.get(0).get(1).all.lose.toString()
                    tvSecondTeamGa.text = d.league.standings.get(0).get(1).all.goals.against.toString()
                    tvSecondTeamGd.text = d.league.standings.get(0).get(1).goalsDiff.toString()
                    tvSecondTeamPts.text = d.league.standings.get(0).get(1).points.toString()
                    Glide.with(itemView.context).load( d.league.standings.get(0).get(1).team.logo).centerCrop().placeholder(R.drawable
                        .ic_launcher_foreground).into(imSecondTeamIcon)


                    //third team
                    tvThirdTeamName.text = d.league.standings.get(0).get(2).team.name
                    tvThirdTeamDraw.text = d.league.standings.get(0).get(2).all.draw.toString()
                    tvThirdTeamLost.text = d.league.standings.get(0).get(2).all.lose.toString()
                    tvThirdTeamGa.text = d.league.standings.get(0).get(2).all.goals.against.toString()
                    tvThirdTeamGd.text = d.league.standings.get(0).get(2).goalsDiff.toString()
                    tvThirdTeamPts.text = d.league.standings.get(0).get(2).points.toString()
                    Glide.with(itemView.context).load( d.league.standings.get(0).get(2).team.logo).centerCrop().placeholder(R.drawable
                        .ic_launcher_foreground).into(imThirdTeamIcon)


                    //fourth team
                    tvFourthTeamName.text = d.league.standings.get(0).get(3).team.name
                    tvFourthTeamDraw.text = d.league.standings.get(0).get(3).all.draw.toString()
                    tvFourthTeamLost.text = d.league.standings.get(0).get(3).all.lose.toString()
                    tvFourthTeamGa.text = d.league.standings.get(0).get(3).all.goals.against.toString()
                    tvFourthTeamGd.text = d.league.standings.get(0).get(3).goalsDiff.toString()
                    tvFourthTeamPts.text = d.league.standings.get(0).get(3).points.toString()
                    Glide.with(itemView.context).load( d.league.standings.get(0).get(3).team.logo).centerCrop().placeholder(R.drawable
                        .ic_launcher_foreground).into(imFourthTeamIcon)


                }

            }
        }
    }
}