package com.example.networkingvolley

import android.app.DownloadManager
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.networkingvolley.databinding.ActivityMainBinding
import com.example.networkingvolley.utils.MyNetwork
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myNetwork: MyNetwork
    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myNetwork = MyNetwork(this)
        requestQueue = Volley.newRequestQueue(this)
        if (myNetwork.isNetworkConnected()){
            binding.tvInfo.text = "connected"
            loadImage(binding.ivInfo,"https://storage.kun.uz/source/thumbnails/_medium/8/UGxGCbMvFAnrwSazGOnfOekuoJainp_i_medium.jpg")
            loadObject(binding.tvInfo,"http://ip.jsontest.com/")
        }else{
            binding.tvInfo.text = "not signal!"
        }
    }

        private fun loadImage(imageView: ImageView, url: String){
            val imageRequest = ImageRequest(url,
                {
                    imageView.setImageBitmap(it)
                },0,0,ImageView.ScaleType.CENTER_CROP,Bitmap.Config.ARGB_8888
            ) {

            }
            requestQueue.add(imageRequest)
        }
        fun loadObject(textView: TextView,url: String){
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
                {
                     val str = it.getString("ip")
                    textView.text = str
                }, {

            })
            requestQueue.add(jsonObjectRequest)
        }
}