package com.example.apiapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.apiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            objectapicallings()

    }

    

    fun objectapicallings(){

        val url = "https://reqres.in/api/users?page=2"
        var requestQueue = Volley.newRequestQueue(this)

        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
            {
                if(it!=null){
                    val page = it.getInt("page")
                    val per_page = it.getInt("per_page")
                    val total = it.getInt("total")
                    val total_pages = it.getInt("total_pages")

                    val supportobject = it.getJSONObject("support")
                    val url = supportobject.getString("url")
                    val text = supportobject.getString("text")

                    val supportmodel = SupportModel(url=url,text=text)

                    val dataJSONArray = it.getJSONArray("data")
                    val listmodel = ArrayList<DataModel>()

                    for(i in 0 until dataJSONArray.length())
                    {
                        val jsonObject = dataJSONArray.getJSONObject(i)
                        val id = jsonObject.getInt("id")
                        val email = jsonObject.getString("email")
                        val first_name = jsonObject.getString("first_name")
                        val last_name = jsonObject.getString("last_name")
                        val avatar = jsonObject.getString("avatar")

                        val model = DataModel(id=id,email=email,first_name=first_name,last_name=last_name,avatar=avatar)

                        listmodel.add(model)
                    }
                    val  apiModel = ApiModel(page=page,per_page=per_page,total=total,total_pages=total_pages,data=listmodel,support=supportmodel)

                    binding.rvdata.adapter = ApiAdapter(apiModel)
                    binding.rvdata.layoutManager = LinearLayoutManager(this)
                }


                Log.i("TAG", "objectapicallings:${it}")

            },
            {
                Log.e("ERROR" ,"objectapicallings: ${it}", )

            })

        requestQueue.add(jsonObjectRequest)

    }

}
