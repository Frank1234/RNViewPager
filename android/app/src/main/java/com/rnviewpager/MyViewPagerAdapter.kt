package com.rnviewpager

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView


class MyViewPagerAdapter(val context: Context,
                         val mReactInstanceManager: ReactInstanceManager) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {

        Log.d("MyViewPagerAdapter", "creating item at $position")

        val mReactRootView = ReactRootView(context)

        val bundle = Bundle()
        bundle.putInt("pageNr", position + 1)

        mReactRootView.startReactApplication(mReactInstanceManager, "RNViewPager", bundle)

        collection.addView(mReactRootView)
        return mReactRootView
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {

        Log.d("MyViewPagerAdapter", "destroying item at $position")

        collection.removeView(view as View)
        (view as ReactRootView).unmountReactApplication()
    }

    override fun getCount(): Int = 50
    override fun isViewFromObject(view: View, o: Any): Boolean = view === o
    override fun getPageTitle(position: Int): CharSequence? = null
}
