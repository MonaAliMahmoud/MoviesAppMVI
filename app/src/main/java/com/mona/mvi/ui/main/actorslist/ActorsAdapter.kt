package com.mona.mvi.ui.main.actorslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mona.mvi.R
import com.mona.mvi.data.model.ActorInfo
import java.util.ArrayList

class ActorsAdapter: RecyclerView.Adapter<ActorsAdapter.ListViewHolder>() {

    private var inflater: LayoutInflater? = null
    private var popularInfo: ActorInfo? = null
    private var info = ArrayList<ActorInfo>()
    private var imgPath = "https://image.tmdb.org/t/p/w500/"
    private var popImg: ImageView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        inflater = LayoutInflater.from(parent.context)
        val listItem = inflater!!.inflate(R.layout.list_item, parent, false)
        return ListViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return info.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        popularInfo = info[position]

        popularInfo?.let {
            holder.bindData(it)
        }
    }

    fun addItems(popularInfo: List<ActorInfo>) {
        info.addAll(popularInfo)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var popName: TextView
        private var popDepart: TextView

        init {
            popImg = itemView.findViewById<View>(R.id.imgActor) as ImageView
            popName = itemView.findViewById<View>(R.id.txtActorName) as TextView
            popDepart = itemView.findViewById<View>(R.id.txtActorDepart) as TextView
        }

        fun bindData(popularInf: ActorInfo) {
            popName.text = popularInf.name
            popDepart.text = popularInf.known_for_department

            popImg?.context?.let { viewContext ->
                Glide.with(viewContext)
                    .load(imgPath + popularInf.profile_path)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(popImg!!)
            }

            itemView.setOnClickListener {
            }
        }
    }
}
