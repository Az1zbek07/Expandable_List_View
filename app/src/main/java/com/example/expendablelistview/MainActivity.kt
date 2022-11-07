package com.example.expendablelistview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import com.example.expendablelistview.adapter.ExpandableAdapter
import com.example.expendablelistview.adapter.OnItemClickListener
import com.example.expendablelistview.util.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableAdapter: ExpandableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Constants.title.add("Football clubs")
        Constants.title.add("Programming languages")

        Constants.subTitle.add(Constants.footballClubs())
        Constants.subTitle.add(Constants.languageList())

        expandableListView = findViewById(R.id.listView)
        expandableAdapter = ExpandableAdapter(this, Constants.title, Constants.subTitle, onItemClickListener)
        expandableListView.setAdapter(expandableAdapter)
    }
    private val onItemClickListener = object : OnItemClickListener{
        override fun onClick(subTitle: String) {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("subTitle", subTitle)
            startActivity(intent)
        }
    }
}