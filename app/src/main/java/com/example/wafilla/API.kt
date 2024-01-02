package com.example.wafilla

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import android.widget.TextView
import androidx.core.content.ContextCompat

data class Vacancy(val id: String, val title: String, val description: String)
interface ApiService {
    @GET("9vZgJMh5zLzlvMCV4NFX") // Предположим, что API hh.ru имеет эндпоинт для получения списка вакансий
    suspend fun getVacancies(): List<Vacancy>
}

class API : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.activity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)
        textView = findViewById(R.id.textView)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.avito.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val vacancies = service.getVacancies()
                textView.text = ""
                vacancies.forEach {
                    textView.append("Вакансия: ${it.title}, Описание: ${it.description}\n")
                }
            } catch (e: Exception) {
                textView.text = "Ошибка: ${e.message}"
            }
        }
    }
}