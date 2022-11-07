package com.example.expendablelistview.adapter

import android.content.Context
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.expendablelistview.R

class ExpandableAdapter(
    private val context: Context,
    private val title: MutableList<String>,
    private val subTitle: MutableList<MutableList<String>>,
    private val onItemClickListener: OnItemClickListener
): BaseExpandableListAdapter() {
    private val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getGroupCount(): Int {
        return title.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return subTitle[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any {
        return title[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return subTitle[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean = false

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var myConverterView = convertView
        if (myConverterView == null){
            myConverterView = inflate.inflate(R.layout.title_layout, null)
        }
        val textView = myConverterView?.findViewById<TextView>(R.id.textView)!!
        textView.text = title[groupPosition]
        return myConverterView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var myConverter = convertView
        if (myConverter == null){
            myConverter = inflate.inflate(R.layout.sub_title_layout, null)
        }
        val textView: TextView = myConverter?.findViewById(R.id.textView)!!
        textView.text = subTitle[groupPosition][childPosition]
        myConverter.setOnClickListener{
            onItemClickListener.onClick(subTitle[groupPosition][childPosition])
        }
        return myConverter
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean = true
}