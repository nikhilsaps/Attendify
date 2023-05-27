package com.sinnikhy.attendify.teacher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sinnikhy.attendify.R
import com.sinnikhy.attendify.datashow.StudRecycAdapter

class TeachClassViewAdapter(private val CLlist: List<CLAModel>) : RecyclerView.Adapter<TeachClassViewAdapter.ViewHolder>(){

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachClassViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_class_recyc, parent, false)

        return TeachClassViewAdapter.ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return CLlist.size
    }

    override fun onBindViewHolder(holder: TeachClassViewAdapter.ViewHolder, position: Int) {
        val ItemsViewModel = CLlist[position]
        holder.classname.text=ItemsViewModel.classname

       // holder.imgcard.setImageResource(R.drawable.person_ic)
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }


    }



    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)  {
        val imgcard: ImageView = itemView.findViewById(R.id.classcard_bg_img)
        val classname: TextView = itemView.findViewById(R.id.cardclass_clasname)
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}