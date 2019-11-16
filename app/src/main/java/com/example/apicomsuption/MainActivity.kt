package com.example.apicomsuption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            try {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://api.myjson.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service: UserService = retrofit.create(UserService::class.java)
                Log.i("Info", "Getting into")
                val call: Call<GUser> = service.getUser()


                call.enqueue(object: Callback<GUser> {
                    override fun onResponse(call: Call<GUser>?, response: Response<GUser>?) {
                        Log.i("Info", "===============")
                        Log.i("Info", response?.body()?.name)
                    }

                    override fun onFailure(call: Call<GUser>?, t: Throwable?) {
                        Log.i("error", t?.message)
                    }
                })

               /* val userO : Any? = call.execute().body()
                userO.let {
                    Log.i("Info", "After info")
                    //Log.i("User", it!![0].name)
                }*/
            } catch (e: Error){
                Log.i("Error", e.message)
                Log.i("Error", e.stackTrace.toString())
            }
        }
    }

}


data class GUser(
    @Expose
    @SerializedName("id")
    var id: Int = 0,
    @Expose
    @SerializedName("name")
    var name: String = "")

interface UserService {
    @GET("/bins/zz9t6")
    fun getUser(): Call<GUser>
}


