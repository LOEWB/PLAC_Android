package com.example.bny.plac_android.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.bny.plac_android.R
import com.example.bny.plac_android.di.DaggerApplicationComponent
import com.example.bny.plac_android.model.Certificate
import com.example.bny.plac_android.services.AuthenticationService
import com.example.bny.plac_android.services.CertificatesService
import com.example.bny.plac_android.ui.data.Result
import com.example.bny.plac_android.ui.login.CertificatesCallback
import com.example.bny.plac_android.ui.login.LoginResult
import com.example.bny.plac_android.ui.login.TokenCallback
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject


class CertificatesActivity : AppCompatActivity(), CertificatesCallback, TokenCallback {

    @Inject
    lateinit var authServ: AuthenticationService
    @Inject
    lateinit var certifServ: CertificatesService

    var certifArrayList: ArrayList<Certificate> = ArrayList()
    lateinit var certifAdapter: CertifAdapter
    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerApplicationComponent.builder()
                .build().injectCertificates(this)

        setContentView(R.layout.activity_certificate)

        val certifListView= findViewById<ListView>(R.id.certif_list_view)


        var certifArrayList: ArrayList<Certificate> = ArrayList()
//        certifArrayList.add(Certificate("DQ-165-25", "27-05-1996", "26-01-2002"))
//        certifArrayList.add(Certificate("GZ-205-03", "20-05-2003", "26-01-2008"))

        this.context = this
        certifAdapter = CertifAdapter(this, certifArrayList)
        certifListView.adapter = certifAdapter
//        authServ.getToken(this)
        certifServ.getCertif("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQaWVycmUiLCJleHAiOjE1ODUyMTY5MjksImlhdCI6MTU4NTE4MDkyOX0.fZbPn2IGXnOiLufTUycL95Uz9XG5RjNNluTJshx7OMA"
        , this, this)
    }

    override fun onSuccessCertificates(res: Result<JSONArray>) {
        if (res is Result.Success) {
            val json: JSONArray = res.data() as JSONArray

            for(i in 0 until json.length()) {
                certifArrayList.add(Certificate(json.getJSONObject(i).getString("formatedImmat"),
                        json.getJSONObject(i).getString("dateFirstImmat"),
                        json.getJSONObject(i).getString("dateBuy")))
            }
            certifAdapter.certifArrayList = certifArrayList

            certifAdapter.notifyDataSetChanged()

        }
    }

    override fun onSuccessToken(res: Result<String>) {
        if (res is Result.Success) {
            val token: String = res.data() as String
            certifServ.getCertif(token, context, this)
        } else {

        }
    }

}

class CertifAdapter(private val context: Context, var certifArrayList: ArrayList<Certificate>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    // override other abstract methods here
    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View? {
        var convertView: View? = convertView
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_certificate_item, container, false)
        }
        val cert: Certificate = certifArrayList[position]

        val immat: TextView? = convertView?.findViewById(R.id.immat_text)
        val firstDate: TextView? = convertView?.findViewById(R.id.date_first_text)
        val dateBuy: TextView? = convertView?.findViewById(R.id.date_buy_text)

        immat?.setText(cert.immat)
        firstDate?.setText(cert.dateFirstImmat)
        dateBuy?.setText(cert.dateBuy)

        return convertView
    }

    override fun getItem(p0: Int): Any {
        return certifArrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return certifArrayList?.size
    }
}
