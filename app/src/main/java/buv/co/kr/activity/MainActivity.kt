package buv.co.kr.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import buv.co.kr.R
import buv.co.kr.components.Item
import buv.co.kr.components.ItemListAdapter
import buv.co.kr.databinding.ActivityMainBinding
import buv.co.kr.fragment.UseFragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var abcd: String = "abcd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        rv_items.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = ItemListAdapter(this, arrayListOf())
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
}
