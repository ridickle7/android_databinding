package buv.co.kr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import buv.co.kr.databinding.ActivityUseFragmentBinding
import kotlinx.android.synthetic.main.activity_use_fragment.*

class UseFragmentActivity : AppCompatActivity() {
    lateinit var binding: ActivityUseFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_use_fragment)
        binding.activity = this

        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(container.id, UseFragment.getInstance())
        transaction.commit()
    }
}