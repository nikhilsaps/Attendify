import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sinnikhy.attendify.PartiStudModel
import com.sinnikhy.attendify.R
import com.sinnikhy.attendify.teacher.CLAModel

class PartClStAdapeter(private val CLlist: List<PartiStudModel>) : RecyclerView.Adapter<PartClStAdapeter.ViewHolder>(){

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartClStAdapeter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.particular_stud_card, parent, false)

        return PartClStAdapeter.ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return CLlist.size
    }

    override fun onBindViewHolder(holder: PartClStAdapeter.ViewHolder, position: Int) {
        val ItemsViewModel = CLlist[position]
        holder.name.text=ItemsViewModel.Stud_name
        holder.course.text = ItemsViewModel.course

        // holder.imgcard.setImageResource(R.drawable.person_ic)
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }


    }



    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)  {
      val name: TextView = itemView.findViewById(R.id.particular_stud_card_name)
        val course :TextView =itemView.findViewById(R.id.particular_stud_card_course)
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}