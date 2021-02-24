package com.tooldev.tvShowReminder.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView

class CustomScrollView: NestedScrollView {

    private var scrollingEnable = true
    private var mOnScrollChangeListener: OnScrollChangeListener? = null
    private var ev: MotionEvent? = null

    constructor(
        context: Context
    ): super(context){}

    constructor(
        context: Context,
        attrs: AttributeSet
    ): super(context, attrs){}

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ): super(context, attrs, defStyleAttr){}

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        this.ev = ev
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if(isScrollingEnable()){
            super.onTouchEvent(ev)
        } else{
            false
        }
    }

    private fun isScrollingEnable(): Boolean{
        return scrollingEnable
    }

    fun scrollingEnable(scrollingEnable: Boolean){
        this.scrollingEnable = scrollingEnable
    }

    interface OnScrollChangeListener{
        fun onScrollChange(
            v: CustomScrollView?, scrollX: Int, scrollY: Int,
            oldScrollX: Int, oldScrollY: Int, ev: MotionEvent?
        )
    }

    fun setOnScrollChangeListener(l: OnScrollChangeListener?) {
        mOnScrollChangeListener = l
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        if (mOnScrollChangeListener != null) {
            mOnScrollChangeListener!!.onScrollChange(this, l, t, oldl, oldt, ev)
        }
    }
}