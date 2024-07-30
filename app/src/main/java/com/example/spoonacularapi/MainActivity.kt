package com.example.spoonacularapi

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.spoonacularapi.api.RetrofitInstance
import com.example.spoonacularapi.model.Namamenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var menuImage: ImageView
    private lateinit var menuName: TextView
    private lateinit var detailNutrisi: TextView
    private lateinit var detailKalori: TextView
    private lateinit var detailpersentaseKalori: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        menuImage = findViewById(R.id.menuImage)
        menuName = findViewById(R.id.menuName)
        detailNutrisi = findViewById(R.id.detailNutrisi)
        detailKalori = findViewById(R.id.detailKalori)
        detailpersentaseKalori = findViewById(R.id.detailpersentaseKalori)

        fetchMenuData()
    }
    private fun fetchMenuData() {
        RetrofitInstance.api.getNamaMenu().enqueue(object : Callback<Namamenu> {
            override fun onFailure(call: Call<Namamenu>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Namamenu>, response: Response<Namamenu>) {
                if (response.isSuccessful) {
                    response.body()?.let { namamenu ->
                        val imageUrl = namamenu.Images.firstOrNull() ?: ""
                        val tittle = namamenu.title

                        val nutrition = namamenu.nutrition
                        val nutrients = nutrition.nutrients.joinToString(", ") {
                            "${it.name}: ${it.amount} ${it.unit}"
                        }

                        val caloricBreakdown = nutrition.caloricBreakdown
                        val percentProtein = caloricBreakdown.percentProtein
                        val percentFat = caloricBreakdown.percentFat
                        val percentCarbs = caloricBreakdown.percentCarbs

                        val calories = nutrition.calories
                        val fat = calories.fat
                        val protein = calories.protein
                        val carbs = calories.carbs

                        val nutrisiDetails = """
                            Nutrients: $nutrients
                        """.trimIndent()

                        val kaloriDetails = """
                            Fat: $fat
                            Protein: $protein
                            Carbs: $carbs
                        """.trimIndent()

                        val persentaseDetails = """
                            Protein: $percentProtein%
                            Fat: $percentFat%
                            Carbs: $percentCarbs%
                        """.trimIndent()

                        Glide.with(this@MainActivity).load(imageUrl).into(menuImage)
                        menuName.text = tittle
                        detailNutrisi.text = nutrisiDetails
                        detailKalori.text = kaloriDetails
                        detailpersentaseKalori.text = persentaseDetails
                    }
                }
            }
        })
    }
    }