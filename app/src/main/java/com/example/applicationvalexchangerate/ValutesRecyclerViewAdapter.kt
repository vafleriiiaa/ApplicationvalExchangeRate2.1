package com.example.applicationvalexchangerate
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class ValutesRecyclerViewAdapter(var valuteList: List<Valute>) :
    RecyclerView.Adapter<ValutesRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView
        val valuteTextView: TextView
        val convertTextView: TextView
        var mRequestQueue: RequestQueue // очередь запросов
        var ValuteJsonObject: JSONObject? = null
        init {
            imageView = view.findViewById(R.id.ValuteImageView)
            valuteTextView = view.findViewById(R.id.ValuteTextView)
            convertTextView = view.findViewById(R.id.ConvertTextView)
            mRequestQueue = Volley.newRequestQueue(view.context);
            val request = JsonObjectRequest(
                Request.Method.GET, /*получение данных*/
                "https://www.cbr-xml-daily.ru/daily_json.js" , null, { response ->
                    try {
                        ValuteJsonObject = response.getJSONObject("Valute")
                    } catch (e: JSONException) {
                        Log.d("Applog", "JSON ERROR")
                        e.printStackTrace()
                    }
                }
            ) { error ->
                Log.d("AppLog", "API ERROR")
                error.printStackTrace()
            }
            mRequestQueue.add(request)
        }
        fun bind(valute: Valute) {
            imageView.setImageResource(valute.imageId)
            if (ValuteJsonObject != null) {
                val valuteJson = ValuteJsonObject!!.getJSONObject(valute.name)
                var value = valuteJson.getDouble("Value")
                val nominal = valuteJson.getInt("Nominal").toDouble()
                val name = valuteJson.getString("Name")
                value = value / nominal
                valuteTextView.text = valute.name + ": " + String.format("%.2f", value) + " ₽"
                convertTextView.text = "за "+ nominal + " " + name
            } else {
                val request = JsonObjectRequest(
                    Request.Method.GET, /*получение данных*/
                    "https://www.cbr-xml-daily.ru/daily_json.js" , null, { response ->
                        try {
                            ValuteJsonObject = response.getJSONObject("Valute")
                            val valuteJson = ValuteJsonObject!!.getJSONObject(valute.name)
                            var value = valuteJson.getDouble("Value")
                            val nominal = valuteJson.getInt("Nominal").toDouble()
                            val name = valuteJson.getString("Name")
                            value = value / nominal
                            valuteTextView.text = valute.name + ": " + String.format("%.2f", value) + " ₽"
                            convertTextView.text = "за "+ nominal + " " + name
                        } catch (e: JSONException) {
                            Log.d("Applog", "JSON ERROR")
                            e.printStackTrace()
                        }
                    }
                ) { error ->
                    Log.d("AppLog", "API ERROR")
                    error.printStackTrace()
                }
                mRequestQueue.add(request)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.valute_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int { return valuteList.size }
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    { holder.bind(valuteList[position]) }
    fun filterList(filterValute: List<Valute>){
        valuteList = filterValute
        notifyDataSetChanged()
    }
}