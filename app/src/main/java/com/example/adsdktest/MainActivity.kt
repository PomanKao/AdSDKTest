package com.example.adsdktest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adsdktest.databinding.ActivityMainBinding
//import com.google.android.gms.ads.AdListener
//import com.google.android.gms.ads.AdLoader
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.LoadAdError
//import com.google.android.gms.ads.MobileAds
//import com.google.android.gms.ads.nativead.NativeAd
//import com.google.android.gms.ads.nativead.NativeAdOptions

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private var recyclerView: RecyclerView? = null
    private var myAdapter: MyAdapter? = null
    private var myList: ArrayList<ItemClass>? = null
//    private var nativeAdList: MutableList<NativeAd>? = null

    private var objects: java.util.ArrayList<Any>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        recyclerView?.setHasFixedSize(true)

        myList = java.util.ArrayList()

        myList!!.add(ItemClass(R.drawable.img_01, "James", "James@email.com"))
        myList!!.add(ItemClass(R.drawable.img_02, "Alex", "Alex@email.com"))
        myList!!.add(ItemClass(R.drawable.img_03, "Ian", "Ian@email.com"))
        myList!!.add(ItemClass(R.drawable.img_04, "Mark", "Mark@email.com"))
        myList!!.add(ItemClass(R.drawable.img_05, "Francesco", "Francesco@email.com"))
        myList!!.add(ItemClass(R.drawable.img_06, "Maria", "Maria@email.com"))
        myList!!.add(ItemClass(R.drawable.img_07, "Mohamed", "Mohamed@email.com"))

        val decorator = DividerItemDecoration(baseContext, LinearLayoutManager.VERTICAL)
        recyclerView?.addItemDecoration(decorator)
        recyclerView?.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

        myAdapter = MyAdapter()
        myAdapter?.setList(myList)

        recyclerView?.adapter = myAdapter

//        nativeAdList = mutableListOf()

        windowManager

        objects = java.util.ArrayList()
        objects!!.add(myList!![0])
        objects!!.add(myList!![1])
        objects!!.add(myList!![2])
        objects!!.add(AdClass(recyclerView))
        objects!!.add(myList!![3])
        objects!!.add(myList!![4])
        objects!!.add(myList!![5])
        objects!!.add(AdClass(recyclerView))
        objects!!.add(myList!![6])
        myAdapter!!.setObject(objects!!)

//        createNativeAd()
    }

//    private fun createNativeAd() {
//        objects = java.util.ArrayList()
//
//        val adClass = AdClass()
//        //---> initializing Google Ad SDK
//        MobileAds.initialize(this) {
//            Log.d(TAG, "Google SDK Initialized")
//            val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
//                .forNativeAd {
//                    fun onNativeAdLoaded(nativeAd: NativeAd) {
//                        Log.d(TAG, "Native Ad Loaded")
//                        if (isDestroyed) {
//                            nativeAd.destroy()
//                            Log.d(TAG, "Native Ad Destroyed")
//                            return
//                        }
//                        nativeAdList?.add(nativeAd)
//                        if (!adClass.adLoader.isLoading) {
//                            objects!!.add(myList!![0])
//                            objects!!.add(myList!![1])
//                            objects!!.add(myList!![2])
//                            objects!!.add(nativeAdList!![0])
//                            objects!!.add(myList!![3])
//                            objects!!.add(myList!![4])
//                            objects!!.add(myList!![5])
//                            objects!!.add(nativeAdList!![1])
//                            objects!!.add(myList!![6])
//                            myAdapter!!.setObject(objects)
//                        }
//                    }
//                }
//                .withAdListener(object:AdListener(){
//                    override fun onAdFailedToLoad(p0: LoadAdError) {
//                        // Handle the failure by logging, altering the UI, and so on.
//                        Log.d(TAG, "Native Ad Failed To Load")
//                        object : CountDownTimer(10000, 1000) {
//                            override fun onTick(millisUntilFinished: Long) {
//                                Log.d(TAG, "Timer : " + millisUntilFinished / 1000)
//                            }
//
//                            override fun onFinish() {
//                                Log.d(TAG, "Reloading NativeAd")
//                                createNativeAd()
//                            }
//                        }.start()
//                    }
//                })
//                .withNativeAdOptions(
//                    NativeAdOptions.Builder()
//                    .build())
//                .build()
//                adLoader.loadAds(AdRequest.Builder().build(), 2)
//                adClass.adLoader = adLoader
//            }
//        }
}
