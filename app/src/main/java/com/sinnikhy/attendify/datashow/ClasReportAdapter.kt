package com.sinnikhy.attendify.datashow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sinnikhy.attendify.R


class ClasReportAdapter(private val CLlist: List<ClaReportModel>) : RecyclerView.Adapter<ClasReportAdapter.ViewHolder>(){

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClasReportAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.class_report_recyc_card, parent, false)

        return ClasReportAdapter.ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return CLlist.size
    }

    override fun onBindViewHolder(holder: ClasReportAdapter.ViewHolder, position: Int) {
        val ItemsViewModel = CLlist[position]
        holder.studname.text="Name :"+ItemsViewModel.Studname
        holder.studroll.text=ItemsViewModel.Studroll
        holder.studprese.text="Status : "+ItemsViewModel.presence
        holder.studdate.text="date : "+ItemsViewModel.date

        // holder.imgcard.setImageResource(R.drawable.person_ic)
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }


    }



    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)  {
        val studname: TextView = itemView.findViewById(R.id.repstudname)
        val studroll: TextView = itemView.findViewById(R.id.reprollno)
        val studdate: TextView = itemView.findViewById(R.id.repstuddate)
        val studprese: TextView = itemView.findViewById(R.id.repstatus)
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}