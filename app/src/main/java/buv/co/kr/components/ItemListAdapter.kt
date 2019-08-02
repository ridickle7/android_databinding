package buv.co.kr.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import buv.co.kr.BR
import buv.co.kr.databinding.ItemItemsBinding
import com.bumptech.glide.Glide


class ItemListAdapter() : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {
    lateinit var context: Context
    var items: ArrayList<Item> = arrayListOf()

    constructor(context: Context, items: ArrayList<Item>) : this() {
        this.context = context
        this.items = items
    }

    fun replaceAll(newItems: ArrayList<Item>) {
        items.apply {
            clear()
            addAll(newItems)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.bind(item)
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
}

@BindingAdapter("imageURL")
fun setImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}