package riflockle7.co.kr.components

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteScrollListener(
    private val linearLayoutManager: LinearLayoutManager,
    private val listener: OnLoadMoreListener?
) : RecyclerView.OnScrollListener() {
    private val POSITION_TOP = -1
    private val POSITION_IDLE = 0
    private val POSITION_BOTTOM = 1

    private var loading: Boolean = false // LOAD MORE Progress dialog
    private var topScrolling: Boolean = false // LOAD MORE Progress dialog
    private var pauseListening = false

    private var END_OF_FEED_ADDED = false
    private val NUM_LOAD_ITEMS = 10

    private var currentPosition = POSITION_TOP

//    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//        super.onScrolled(recyclerView, dx, dy)
//        if (dx == 0 && dy == 0)
//            return
//        val totalItemCount = linearLayoutManager.itemCount
//        val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
//        // Log.d("onScrolled ", "$dx / $dy")
//
//        // 로딩중이 아니고 && 전체 itemCount
//        if (!loading && totalItemCount <= lastVisibleItem + VISIBLE_THRESHOLD && totalItemCount != 0 && !END_OF_FEED_ADDED && !pauseListening) {
//            Log.i("onScrolled", "End of list")
//            listener?.onLoadMore()
//            loading = true
//        }
//    }

    // recyclerView의 ScrollState를 체크하여 List의 어느 부분이 보이는지 체크한다
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        // Log.d("currentPosition", "$currentPosition")

        // 최상단에 처음 도달한 경우
        if (!topScrolling && !loading && !recyclerView.canScrollVertically(POSITION_TOP) && currentPosition != POSITION_TOP) {
            Log.i("onScrollStateChanged", "Top of list arrived")
            currentPosition = POSITION_TOP
            listener?.onLoadRefresh()
            loading = true
            topScrolling = true
        }
        // 계속 최상단에 있는데 위로 스크롤 하는 경우 (refresh 후, 최상단 item을 보여줘야 하기 때문에 아래의 작업이 필요함)
        else if (!recyclerView.canScrollVertically(POSITION_TOP)) {
            Log.i("onScrollStateChanged", "already Top arrived")
            currentPosition = POSITION_TOP
        }

        // 최하단에 처음 도달한 경우 (리스트 최하단 item 보여줄 필요 없으므로 아래 식만 남김)
        else if (!loading && !recyclerView.canScrollVertically(POSITION_BOTTOM) && currentPosition != POSITION_BOTTOM) {
            Log.i("onScrollStateChanged", "End of list arrived")
            currentPosition = POSITION_BOTTOM
            listener?.onLoadMore()
            loading = true
        }

        // 이 외의 경우 POSITION은 IDLE 상태
        else {
            if (topScrolling && currentPosition != POSITION_IDLE){
                Log.i("onScrollStateChanged", "idle : topScrolling = false")
                topScrolling = false
            }
            else {
                Log.i("onScrollStateChanged", "idle : currentPosition = POSITION_IDLE")
                currentPosition = POSITION_IDLE
            }
        }
    }

    // 로딩 완료 처리
    fun setLoaded() {
        loading = false
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
        fun onLoadRefresh()
    }

    fun addEndOfRequests() {
        this.END_OF_FEED_ADDED = true
    }

    fun pauseScrollListener(pauseListening: Boolean) {
        this.pauseListening = pauseListening
    }

    companion object {

        private val VISIBLE_THRESHOLD = 2
    }
}
