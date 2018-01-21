package com.paranoid.mao.expandablerecyclerviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataList = List(26, { i ->
            DummyData("Header ${'A' + i}", "Content ${i + 1}")
        })

        expandableRecycleView.apply {
            val manager = LinearLayoutManager(this@MainActivity)
            layoutManager = manager
            adapter = ExpandableAdapter(dataList, this)
            itemAnimator = Animator()
            addItemDecoration(DividerItemDecoration(this@MainActivity, manager.orientation))
        }

    }
}
