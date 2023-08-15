package com.example.adsdktest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admodule.AdSDK
import com.example.adsdktest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private var recyclerView: RecyclerView? = null
    private var myAdapter: MyAdapter? = null
    private var myList: ArrayList<ItemClass>? = null

    private var objects: java.util.ArrayList<Any>? = null

    private val names = arrayOf("James", "Alex", "Ian", "Mark", "Francesco", "Maria", "Mohamed")
    private val emails = arrayOf("James@email.com", "Alex@email.com", "Ian@email.com", "Mark@email.com", "Francesco@email.com", "Maria@email.com", "Mohamed@email.com")
    private val images = arrayOf(R.drawable.img_01, R.drawable.img_02, R.drawable.img_03, R.drawable.img_04, R.drawable.img_05, R.drawable.img_06, R.drawable.img_07)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        recyclerView?.setHasFixedSize(true)

        myList = java.util.ArrayList()

        for (i in 0..90) {
            myList!!.add(ItemClass(images[i % 7], names[i % 7], emails[i % 7]))
        }
        val decorator = DividerItemDecoration(baseContext, LinearLayoutManager.VERTICAL)
        recyclerView?.addItemDecoration(decorator)
        recyclerView?.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

        val adSDK = AdSDK.getInstance(binding.recyclerView)
        adSDK.setAdDelta(3)
        myAdapter = MyAdapter()

        recyclerView?.adapter = myAdapter

        objects = java.util.ArrayList()
        for (i in 0..myList!!.lastIndex) {
            objects!!.add(myList!![i])
            if (i > 0 && i % adSDK.getAdDelta() == 0)
                objects!!.add(i, adSDK.getAdClass())
        }

        myAdapter!!.setObject(objects!!)

    }

}
