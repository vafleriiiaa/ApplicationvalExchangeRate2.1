package com.example.applicationvalexchangerate
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {
    lateinit var valutesRecyclerView: RecyclerView
    lateinit var searchValuteEditText: EditText
    lateinit var adapter: ValutesRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        valutesRecyclerView = findViewById(R.id.ValutesRecyclerView)
        searchValuteEditText = findViewById(R.id.searchValuteEditText)
        val valutesList = listOf<Valute>(
            Valute("USD", R.drawable.usd),
            Valute("EUR", R.drawable.eur),
            Valute("CNY", R.drawable.cny),
            Valute("BYN", R.drawable.byn),
            Valute("AUD", R.drawable.aud),
            Valute("AZN", R.drawable.azn),
            Valute("GBP", R.drawable.gbp),
            Valute("AMD", R.drawable.amd),
            Valute("BGN", R.drawable.bgn),
            Valute("BRL", R.drawable.brl),
            Valute("HUF", R.drawable.huf),
            Valute("VND", R.drawable.vnd),
            Valute("HKD", R.drawable.hkd),
            Valute("GEL", R.drawable.gel),
            Valute("DKK", R.drawable.dkk),
            Valute("AED", R.drawable.aed),
            Valute("EGP", R.drawable.egp),
            Valute("INR", R.drawable.inr),
            Valute("IDR", R.drawable.idr),
            Valute("KZT", R.drawable.kzt),
            Valute("CAD", R.drawable.cad),
            Valute("QAR", R.drawable.qar),
            Valute("KGS", R.drawable.kgs),
            Valute("MDL", R.drawable.mdl),
            Valute("NZD", R.drawable.nzd),
            Valute("NOK", R.drawable.nok),
            Valute("PLN", R.drawable.pln),
            Valute("RON", R.drawable.ron),
            Valute("XDR", R.drawable.xdr),
            Valute("SGD", R.drawable.sgd),
            Valute("TJS", R.drawable.tjs),
            Valute("THB", R.drawable.thb),
            Valute("TRY", R.drawable.ttry),
            Valute("TMT", R.drawable.tmt),
            Valute("UZS", R.drawable.uzs),
            Valute("UAH", R.drawable.uan),
            Valute("CZK", R.drawable.czk),
            Valute("SEK", R.drawable.sek),
            Valute("CHF", R.drawable.chf),
            Valute("RSD", R.drawable.rsd),
            Valute("ZAR", R.drawable.zar),
            Valute("KRW", R.drawable.krw),
            Valute("JPY", R.drawable.jpy))
        searchValuteEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val newValuteList = valutesList.filter {
                        it.name.contains(s.toString(), true)
                    }
                    adapter.filterList(newValuteList)
                }
                override fun afterTextChanged(s: Editable?) { }
            }
        )
        adapter = ValutesRecyclerViewAdapter(valutesList)
        valutesRecyclerView.adapter = adapter
    }
}