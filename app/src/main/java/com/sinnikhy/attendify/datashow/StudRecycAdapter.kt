package com.sinnikhy.attendify.datashow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sinnikhy.attendify.R
import com.sinnikhy.attendify.StudDataModel


class StudRecycAdapter(private val mList: List<StudDataModel>) : RecyclerView.Adapter<StudRecycAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stud_show_recyc_card, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class
        holder.rolnum.text=ItemsViewModel.rollno
        holder.name.text = ItemsViewModel.name
        holder.course.text = ItemsViewModel.course
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)  {
        val rolnum: TextView = itemView.findViewById(R.id.studrollnum)
        val name: TextView = itemView.findViewById(R.id.studrecycnametext)
        val course: TextView = itemView.findViewById(R.id.studrecyccoursetext)
    }
}