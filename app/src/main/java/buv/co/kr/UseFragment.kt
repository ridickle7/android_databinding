package buv.co.kr

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import buv.co.kr.databinding.FragmentUseBinding

class UseFragment : Fragment() {
    var fragmentName = ObservableField<String>("ridickle7")
    lateinit var binding : FragmentUseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // var view = inflater.inflate(R.layout.fragment_use, container, false)
        // return view
        fragmentName.set("${fragmentName.get()} hi")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_use, container, false)
        binding.fragment = this

        return binding.root
    }

    companion object {
        var INSTANCE: UseFragment? = null

        @JvmStatic
        fun getInstance(): UseFragment {
            if (INSTANCE == null)
                INSTANCE = UseFragment()
            return INSTANCE!!
        }
    }
}
