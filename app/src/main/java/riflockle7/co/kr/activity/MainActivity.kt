package riflockle7.co.kr.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import riflockle7.co.kr.R
import riflockle7.co.kr.components.InfiniteScrollListener
import riflockle7.co.kr.components.Item
import riflockle7.co.kr.components.ItemListAdapter
import riflockle7.co.kr.databinding.ActivityMainBinding
import riflockle7.co.kr.fragment.UseFragmentActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), InfiniteScrollListener.OnLoadMoreListener {
    lateinit var binding: ActivityMainBinding
    var abcd: String = "abcd"

    lateinit var adapter: ItemListAdapter
    lateinit var infiniteScrollListener: InfiniteScrollListener
    var mainHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        infiniteScrollListener = InfiniteScrollListener(layoutManager, this)
        infiniteScrollListener.setLoaded()

        rv_items.layoutManager = layoutManager
        rv_items.addOnScrollListener(infiniteScrollListener);
        adapter = ItemListAdapter(this, arrayListOf())
        rv_items.adapter = adapter

        adapter.replaceAll(getDummyList())
    }

    fun textClick() {
        startActivity(Intent(this@MainActivity, UseFragmentActivity::class.java))
    }

    private fun getDummyList(): ArrayList<Item> {
        val tempList = arrayListOf<Item>()

        for (i in 0..9) {
            tempList.add(
                Item(
                    "과자$i",
                    "https://taegon.kim/wp-content/uploads/2018/05/image-9.png"
                )
            )
        }

        return tempList
    }

    override fun onLoadMore() {
        // Log.d("onLoadMore", " called")
        adapter.addProgressData()
        adapter.notifyItemInserted(rv_items.layoutManager!!.itemCount)
        rv_items.smoothScrollToPosition(rv_items.layoutManager!!.itemCount)

        mainHandler.postDelayed({
            adapter.removeProgressData()
            val newData = getDummyList()
            adapter.addAll(newData)

            infiniteScrollListener.setLoaded()
        }, 2000)
    }

    override fun onLoadRefresh() {
        // Log.d("onLoadRefresh", " called")
        adapter.addProgressData(0)
        adapter.notifyItemInserted(0)
        rv_items.smoothScrollToPosition(0)

        mainHandler.postDelayed({
            adapter.removeProgressData(0)
            val newData = getDummyList()
            adapter.replaceAll(newData)

            rv_items.smoothScrollToPosition(0)
            infiniteScrollListener.setLoaded()
        }, 2000)
    }
}
