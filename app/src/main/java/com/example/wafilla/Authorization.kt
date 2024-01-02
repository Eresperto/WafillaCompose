package com.example.wafilla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch

class Authorization : AppCompatActivity() {
    var heredostup: String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.activity)
        setContentView(R.layout.authorization)
        var edt_log = findViewById<EditText>(R.id.edt_log)
        var edt_pass = findViewById<EditText>(R.id.edt_pass)
        var btn_autoreg = findViewById<Button>(R.id.btn_autoreg)
        var btn_reg = findViewById<Button>(R.id.btn_reg)
        val intent_reg = Intent(this, Registration::class.java)
        val intentlk_comp = Intent(this, LKcompanies::class.java)
        val intentlk_str_up = Intent(this, LKstartup::class.java)


        btn_reg.setOnClickListener {
            startActivity(intent_reg)
        }
        btn_autoreg.setOnClickListener {
            var log = edt_log.text.toString()
            var pas = edt_pass.text.toString()
            getData(log,pas)
            startActivity(intentlk_comp)
        }



    }
    var a:Int = 0
    private fun getData(log:String,pas:String ){
        lifecycleScope.launch {
            val client = getClient()
            val supabaseReponse = client.postgrest["users"].select()
            val data = supabaseReponse.decodeList<users>()

            while(a<data.size){
                if((log==data[a].userlogin) and (pas==data[a].userpass)){
                    heredostup=data[a].power


                }


            }




        }
    }


    private fun getClient(): SupabaseClient {
        return createSupabaseClient(supabaseUrl = "https://twrnebqcmxfljyozuqyp.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InR3cm5lYnFjbXhmbGp5b3p1cXlwIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTkyNzMwODYsImV4cCI6MjAxNDg0OTA4Nn0.NKu0iwKego8G5OJOTz2bay3OOsouPvtMjQZee219Ksg")
        {
            install(Postgrest)
        }
    }

}
@kotlinx.serialization.Serializable
data class users(
    val id: Int=0,
    val username: String = "",
    val userlogin: String = "",
    val userpass: String = "",
    val power: String=""
){

    override fun toString(): String{
        return "${id} ${username} ${userlogin} ${userpass} ${power}"



    }
}