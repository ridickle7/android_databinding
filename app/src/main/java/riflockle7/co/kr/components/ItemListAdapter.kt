package riflockle7.co.kr.components

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import riflockle7.co.kr.BR
import riflockle7.co.kr.R
import riflockle7.co.kr.databinding.ItemItemsBinding
import com.bumptech.glide.Glide

class ItemListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context
    var items: ArrayList<Item> = arrayListOf()

    var VIEW_TYPE_ITEM = 0
    var VIEW_TYPE_LOADING = 1

    constructor(context: Context, items: ArrayList<Item>) : this() {
        this.context = context
        this.items = items
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].imageURL != "progress")
            VIEW_TYPE_ITEM
        else
            VIEW_TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding = ItemItemsBinding.inflate(LayoutInflater.from(context), parent, false)
            ViewHolder(binding)
        } else {
            Log.d("getItemViewType ", "progressing")
            val view = LayoutInflater.from(context).inflate(R.layout.item_progress, parent, false)
            ProgressHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (item.imageURL != "progress") {
            (holder as ViewHolder).bind(item)
        } else {

        }
    }

    fun addProgressData(position: Int = items.size) {
        if (position != 0) {
            items.add(getProgressItem())
        } else {
            items.add(position, getProgressItem())
        }
    }

    fun removeProgressData(position: Int = items.size - 1) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun replaceAll(newItems: ArrayList<Item>) {
        items.apply {
            clear()
            addAll(newItems)
            notifyDataSetChanged()
        }
    }

    fun addAll(newItems: ArrayList<Item>) {
        items.apply {
            addAll(newItems)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ItemItemsBinding

        constructor(binding: ItemItemsBinding) : this(binding.root) {
            this.binding = binding
        }

        // item_my.xml : <variable> 정보를 취함
        fun bind(item: Item) {
            binding.setVariable(BR.item, item)
        }
    }

    class ProgressHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView) {
            Log.d("ProgressHolder", " completed")
        }

    }
}

@BindingAdapter("imageURL")
fun setImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}