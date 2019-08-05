package riflockle7.co.kr.components

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteScrollListenerBottom(
    private val listener: OnLoadMoreListener?
) : RecyclerView.OnScrollListener() {
    private val SCROLLING_TOP = -1
    private val SCROLLING_BOTTOM = 1

    private var loading: Boolean = false      // 데이터 로딩 중일 때 실행


    // recyclerView의 ScrollState를 체크하여 List의 어느 부분이 보이는지 체크한다
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        // Log.d("currentPosition", "$currentPosition")

        // 최상단에 처음 도달한 경우
        if (!recyclerView.canScrollVertically(SCROLLING_TOP)) {
            Log.i("onScrollStateChanged", "Top of list arrived")
        }

        // 최하단에 도달한 경우
        else if (!loading && !recyclerView.canScrollVertically(SCROLLING_BOTTOM)) {
            Log.i("onScrollStateChanged", "End of list arrived")
            listener?.onLoadMore()
            loading = true
        }

        // 이 외의 경우 POSITION은 IDLE 상태
        else {
            Log.i("onScrollStateChanged", "Idle")
        }
    }

    // 로딩 완료 처리
    fun setLoaded() {
        loading = false
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}
