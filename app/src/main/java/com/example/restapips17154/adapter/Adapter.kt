import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restapips17154.R
import com.example.restapips17154.api.Post
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class Adapter(private val mList: List<Post>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val clickSubject = PublishSubject.create<String>()
    val clickEvent : Observable<String> = clickSubject
    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView1: TextView = itemView.findViewById(R.id.commenttext)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        init {
            itemView.setOnClickListener {
                clickSubject.onNext(textView1.text as String)
            }
        }
    }

//    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
////        val imageView: ImageView = itemView.findViewById(R.id.imageview)
////        val textView: TextView = itemView.findViewById(R.id.textView)
//
//    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

//        // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageResource(ItemsViewModel.image)
//
//        // sets the text to the textview from our itemHolder class
//        holder.textView.text = ItemsViewModel.text

            holder.textView1.text = item.id.toString()
            holder.textView2.text = item.title

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text

}
