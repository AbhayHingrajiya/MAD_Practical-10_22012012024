package com.example.mad_practical_10_22012012024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button=findViewById<Button>(R.id.flotingButton).setOnClickListener(){
            sendDataToListView()
        }
    }

    private fun getPersonDetailsFromJson(sJson: String?) {
        val personList = ArrayList<Contect>()
        try {
            val jsonArray = JSONArray(sJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val person = Contect(jsonObject)
                personList.add(person)
            }
            val personListView=findViewById<ListView>(R.id.listView1)
            personListView.adapter = ContactAdapter(this, personList)
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }
    }
    fun sendDataToListView(){
//        val personListView=findViewById<ListView>(R.id.listView1)
//        val personList= arrayListOf<Contect>(
//            Contect("AB123","Abhay","abhayhingrajiya18@gmail.com","7874738478","Rajkot",1.234,1.23),
//            Contect("AB123","Abhay","abhayhingrajiya18@gmail.com","7874738478","Rajkot",2.34,1.23),
//            Contect("AB123","Abhay","abhayhingrajiya18@gmail.com","7874738478","Rajkot",2.31,3.12),
//            Contect("AB123","Abhay","abhayhingrajiya18@gmail.com","7874738478","Rajkot",1.23,1.23),
//            Contect("AB123","Abhay","abhayhingrajiya18@gmail.com","7874738478","Rajkot",1.22,1.55)
//        )
//        personListView.adapter=ContactAdapter(this,personList)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = HttpRequest().makeServiceCall(
                    "https://api.json-generator.com/templates/qjeKFdjkXCdK/data",
                    "rbn0rerl1k0d3mcwgw7dva2xuwk780z1hxvyvrb1")
                withContext(Dispatchers.Main) {
                    try {
                        if(data != null)
                            runOnUiThread{getPersonDetailsFromJson(data)}
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }


}