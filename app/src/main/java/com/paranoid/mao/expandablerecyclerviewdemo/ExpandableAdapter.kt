package com.paranoid.mao.expandablerecyclerviewdemo

import android.support.v7.widget.RecyclerView
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class ExpandableAdapter(private var dataList: List<DummyData>, private val parent: RecyclerView): RecyclerView.Adapter<ExpandableAdapter.ViewHolder>() {

    companion object {
        const val EXPAND_COLLAPSE = "EXPAND_COLLAPSE"
    }

    private var expandedPosition = RecyclerView.NO_POSITION
    private val transition = AutoTransition().apply { duration = 100 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>?) {
        if (payloads?.contains(EXPAND_COLLAPSE) == true) {
            setExpanded(holder, expandedPosition == position)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bind(dataList[position])
            itemView.setOnClickListener {
                TransitionManager.beginDelayedTransition(parent, transition)
                // collapse currently expanded items
                if (RecyclerView.NO_POSITION != expandedPosition) {
                    notifyItemChanged(expandedPosition, EXPAND_COLLAPSE)
                }

                // expand this item
                if (expandedPosition != adapterPosition) {
                    expandedPosition = adapterPosition
                    notifyItemChanged(adapterPosition, EXPAND_COLLAPSE)
                } else {
                    expandedPosition = RecyclerView.NO_POSITION
                }
            }
            setExpanded(this, expandedPosition == position)
        }
    }

    private fun setExpanded(holder: ViewHolder, isExpanded: Boolean) {
        val visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.itemView.apply {
            isActivated = isExpanded
            contentView.visibility = visibility
        }
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(data: DummyData) = with(itemView) {
            headerView.text = data.header
            contentView.text = data.content
        }
    }
}