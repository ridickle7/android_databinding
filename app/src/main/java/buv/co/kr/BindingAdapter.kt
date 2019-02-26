package buv.co.kr

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["abcd", "abcde"])
fun abcd(view: TextView, abcd : Boolean, abcde : Boolean){
    view.text = if(abcd || abcde) "맞았다" else "틀렸다"
}
