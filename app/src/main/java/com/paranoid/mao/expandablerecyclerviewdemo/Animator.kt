package com.paranoid.mao.expandablerecyclerviewdemo

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView

class Animator(): DefaultItemAnimator() {
    // Invalid recycler view moves items which causes flash when expanding or collapsing
    override fun animateMove(holder: RecyclerView.ViewHolder?, fromX: Int, fromY: Int, toX: Int, toY: Int): Boolean {
        return false
    }
}