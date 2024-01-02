package com.example.wafilla

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

class Creatingproject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.activity)
        setContentView(R.layout.creatingproject)
        var edt_fio = findViewById<EditText>(R.id.edt_fio)
        var edt_idea = findViewById<EditText>(R.id.edt_idea)
        var edt_nameproject = findViewById<EditText>(R.id.edt_nameproject)
        var edt_forwhom = findViewById<EditText>(R.id.edt_forwhom)
        var edt_tasks = findViewById<EditText>(R.id.edt_tasks)
        var btn_next = findViewById<Button>(R.id.btn_next)


        btn_next.setOnClickListener {
            insertData(edt_fio.text.toString(),
                edt_idea.text.toString(),
                edt_nameproject.text.toString(),
                edt_forwhom.text.toString(),
                edt_tasks.text.toString())

        }


    }

    private fun insertData(
        name: String,
        pass: String,
        dostup: String,
        target: String,
        task: String
    ) {
        lifecycleScope.launch {
            val client = getClient()
            var abc = ideas(
                username_p = name,
                description_p = pass,
                projectname = dostup,
                target = target,
                task = task
            )
            client.postgrest["project"].insert(value = abc)

        }
    }

    private fun getClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://fxbekrnqirhjrdfwlrcg.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZ4YmVrcm5xaXJoanJkZndscmNnIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTg2Njg3NDQsImV4cCI6MjAxNDI0NDc0NH0.IJ74XiV00wtQFaXC6dU9LboD0pF7n9I9LhsmSwSZ2pg"
        )
        {
            install(Postgrest)
        }

    }
}
@kotlinx.serialization.Serializable
data class ideas(
    val id: Int=0,
    val username_p: String = "",
    val description_p: String = "",
    val projectname: String="",
    val target: String="",
    val task: String =""
){
    override fun toString(): String{
        return "${id} ${username_p} ${description_p} ${projectname} ${target} ${task}"
    }
}
