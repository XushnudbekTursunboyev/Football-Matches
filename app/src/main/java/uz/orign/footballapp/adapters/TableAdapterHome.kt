package uz.orign.footballapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.DelicateCoroutinesApi
import uz.orign.footballapp.R
import uz.orign.footballapp.databinding.TeamItemBinding
import uz.orign.footballapp.network.model.standing_league.Standing


class TableAdapterHome() : RecyclerView.Adapter<TableAdapterHome.Vh>() {
    lateinit var context: Context
    lateinit var tascount: ArrayList<Int>
    lateinit var viewowner: Fragment
    lateinit var list: List<Standing>
    var isTask: Boolean = false
    lateinit var onpress: onPress
    var oldItem = -1
    var itemViewList = ArrayList<TeamItemBinding>()

    constructor(context: Context, tascount: ArrayList<Int>, list: List<Standing>, isTask: Boolean = false, onpress: onPress) : this() {
        this.context = context
        this.tascount = tascount
        this.list = list
        this.isTask = isTask
        this.onpress = onpress
    }

    inner class Vh(var itemview: TeamItemBinding) : RecyclerView.ViewHolder(itemview.root) {
        @OptIn(DelicateCoroutinesApi::class)
        @SuppressLint("ResourceAsColor", "NotifyDataSetChanged", "SetTextI18n")
        fun bind(d: Standing, position: Int) {
        itemview.apply {
            tvCount.text = (adapterPosition+1).toString()
            tvFirstTeamName.text = d.team.name
            tvFirstTeamDraw.text = d.home.draw.toString()
            tvFirstTeamLost.text = d.home.lose.toString()
            tvFirstTeamGa.text = d.home.goals.against.toString()
            tvFirstTeamGd.text = d.goalsDiff.toString()
            tvFirstTeamPts.text = d.points.toString()
            Glide.with(itemView.context).load( d.team.logo).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(imFirstTeamIcon)

            linear.setOnClickListener {
                if (isTask) {
                    if (oldItem < 0) {
                        itemViewList[position].constraint.setBackgroundResource(R.drawable.linear_select)
                        itemViewList[position].linear.setBackgroundResource(R.drawable.linear_select)
                    } else {
                        itemViewList[oldItem].constraint.setBackgroundResource(R.drawable.linear_back)
                        itemViewList[oldItem].linear.setBackgroundResource(R.drawable.linear_back)
                        itemViewList[position].constraint.setBackgroundResource(R.drawable.linear_select)
                        itemViewList[position].linear.setBackgroundResource(R.drawable.linear_select)
                    }
                    onpress.selected(position, oldItem, itemViewList, itemview, d)
                    oldItem = position
                } else {
                    onpress.click(d)
                }
            }
        }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        var vh = Vh(TeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        itemViewList.add(vh.itemview)
        return vh
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.bind(list[position], position)

    }

    override fun getItemCount(): Int = list.size

    interface onPress {
        fun selected(
            position: Int,
            oldItem: Int,
            list: ArrayList<TeamItemBinding>,
            itemview: TeamItemBinding,
            d: Standing
        )

        fun click(d: Standing)
    }

}