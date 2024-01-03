package com.example.wafilla

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.wafilla.ui.theme.WafillaTheme
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch


class MainCompanies : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       lifecycleScope.launch {

           var PrjNamemas = getProjectName()
           var PrjCostmas=getProjectCost()
           var Prjlongmas=getProjectLong()

           setContent {
               WafillaTheme {
                   // A surface container using the 'background' color from the theme
                   Surface(
                       modifier = Modifier.fillMaxSize(),
                       color = MaterialTheme.colorScheme.background
                   ) {
                       //Тут элементы UI
                        listview(context =applicationContext, prjnames =PrjNamemas, cost =PrjCostmas , long = Prjlongmas)
                       Shapka()
                   }
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

    private suspend fun getProjectName():Array<String> {
        var client = getClient()
        var supabaseReponse = client.postgrest["CostPrj"].select()
        return supabaseReponse.decodeList<CostPrj>().map { it.prjname }.toTypedArray()
    }
    private suspend fun getProjectCost():Array<String> {
        var client = getClient()
        var supabaseReponse = client.postgrest["CostPrj"].select()
        return supabaseReponse.decodeList<CostPrj>().map { it.prjcost }.toTypedArray()
    }
    private suspend fun getProjectLong():Array<String> {
        var client = getClient()
        var supabaseReponse = client.postgrest["CostPrj"].select()
        return supabaseReponse.decodeList<CostPrj>().map { it.long }.toTypedArray()
    }

    @Composable
    fun Shapka(){
        Row(Modifier/*.fillMaxWidth()*/) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.group45), contentDescription = "topBAr", contentScale = ContentScale.FillWidth)
        }


    }

    @Composable
    fun listview(context: Context,prjnames: Array<String>,cost: Array<String>, long : Array<String>) {
        //val longarray = arrayOf("a","b","c")



        Card(
            modifier = Modifier
                .padding(top = 46.dp)
                .background(
                    Color(red = 27, green = 45, blue = 59),
                    shape = RoundedCornerShape(20.dp)
                )
                .fillMaxSize(), shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color(red = 27, green = 45, blue = 59))
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.padding(top = 10.dp, start = 50.dp),
                    text = "Здравствуйте , ", color = Color.White, textAlign = TextAlign.Center,
                    fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(start = 30.dp, top = 30.dp),
                    text = "Инвестируемы проекты"

                )
                //Тут должен быть цикл
                LazyColumn(
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color(red = 27, green = 45, blue = 59))
                        .padding(bottom= 40.dp)
                ) {
                    // on below line we are setting data for each item of our listview.
                    var trueIndex: Int = 0
                    var rasstan: Boolean = false

                    itemsIndexed(prjnames) { index, item ->
                        // on below line we are creating a card for our list view item.
                        if (rasstan == false) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(red = 27, green = 45, blue = 59)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                //Тут цикл
                                Column(
                                    // for our row we are adding modifier
                                    // to set padding from all sides.
                                    modifier = Modifier
                                        .padding(top = 8.dp, end = 20.dp)
//                                  .fillMaxWidth(0.31f)
//                                  .fillMaxHeight(0.2f)
                                        .border(0.dp, Transparent)
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(
                                                    Color(red = 100, green = 179, blue = 166),
                                                    Color(red = 45, green = 80, blue = 83),
                                                )
                                            ), shape = RoundedCornerShape(15.dp)
                                        )
                                        .clip(shape = RoundedCornerShape(15.dp)),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // on below line inside row we are adding spacer
                                    Spacer(modifier = Modifier.width(5.dp))
                                    // on below line we are adding spacer between image and a text
                                    Spacer(modifier = Modifier.width(5.dp))
                                    // on the below line we are creating a text.
                                    Text(
                                        modifier = Modifier.padding(top = 10.dp),
                                        text = prjnames[trueIndex],
                                        color = Color.White, textAlign = TextAlign.Center,
                                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 10.dp),
                                        text = "стоимость", fontSize = 15.sp
                                    )
                                    Text(
                                        text = cost[trueIndex] + " руб.",
                                        modifier = Modifier.padding(4.dp),
                                        // on below line we are adding color for our text

                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = " время разработки",
                                        modifier = Modifier.padding(top = 10.dp), fontSize = 15.sp
                                    )
                                    Text(
                                        text = long[trueIndex],
                                        // on below line we are adding color for our text
                                        color = Color.White, textAlign = TextAlign.Center,
                                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                                    )
                                    if (trueIndex < prjnames.size - 1) {
                                        trueIndex += 1
                                    } else if (trueIndex == prjnames.size - 1) {
                                        rasstan = true
                                    }

                                    Button(
                                        modifier = Modifier
                                            .padding(top = 10.dp, bottom = 10.dp),
                                        onClick = { /*TODO*/ },
                                    ) {
                                        Text(
                                            text = "Подробнее",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                // on below line we are creating
                                // a row for our list view item.
                                Column(
                                    // for our row we are adding modifier
                                    // to set padding from all sides.
                                    modifier = Modifier
                                        .padding(8.dp)
//                                  .fillMaxWidth(0.5f)
//                                  .fillMaxHeight(0.2f)
                                        .fillMaxHeight(0.35f)
                                        .border(0.dp, Transparent)
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(
                                                    Color(red = 100, green = 179, blue = 166),
                                                    Color(red = 45, green = 80, blue = 83),
                                                )
                                            ), shape = RoundedCornerShape(15.dp)
                                        )
                                        .clip(shape = RoundedCornerShape(15.dp)),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // on below line inside row we are adding spacer
                                    Spacer(modifier = Modifier.width(5.dp))
                                    // on below line we are adding spacer between image and a text
                                    Spacer(modifier = Modifier.width(5.dp))
                                    // on the below line we are creating a text.
                                    Text(
                                        modifier = Modifier.padding(top = 10.dp),
                                        text = prjnames[trueIndex],
                                        color = Color.White, textAlign = TextAlign.Center,
                                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 10.dp),
                                        text = "стоимость", fontSize = 15.sp
                                    )
                                    Text(
                                        text = cost[trueIndex] + " руб.",
                                        modifier = Modifier.padding(4.dp),
                                        // on below line we are adding color for our text
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = " время разработки",
                                        modifier = Modifier.padding(top = 10.dp), fontSize = 15.sp
                                    )
                                    Text(
                                        text = long[trueIndex],
                                        // on below line we are adding color for our text
                                        color = Color.White, textAlign = TextAlign.Center,
                                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                                    )
                                    if (trueIndex < prjnames.size - 1) {
                                        trueIndex += 1
                                    } else if (trueIndex == prjnames.size - 1) {
                                        rasstan = true
                                    }
                                    Button(
                                        modifier = Modifier
                                            .padding(top = 10.dp, bottom = 10.dp),


                                        // colors = ButtonDefaults.buttonColors()


                                        shape = RoundedCornerShape(15.dp),
                                        onClick = { /*TODO*/ },
                                    ) {
                                        Text(
                                            text = "Подробнее",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }


                            }
                        } else {

                        }


                    }
                }
            }
        }

    }
    
    @kotlinx.serialization.Serializable
    data class CostPrj(
        val id: Int=0,
        val prjname: String = "",
        val prjcost: String = "",
        val long: String = "",
    ){
        override fun toString(): String{
            return "${id} ${prjname} ${prjcost} ${long}"
        }
    }
    @kotlinx.serialization.Serializable
    data class users2(
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

}
