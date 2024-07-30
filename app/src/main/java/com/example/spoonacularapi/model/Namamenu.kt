package com.example.spoonacularapi.model

data class Namamenu(
    val id: Int,
    val title: String,
    val nutrition: Nutrition,
    val Image: Image,
    val Images: List<String>
)

data class Nutrition(
    val nutrients: List<Nutrients>,
    val caloricBreakdown: CaloricBreakdown,
    val calories: Calories,
    val fat: String,
    val protein: String,
    val carbs: String
)

data class Nutrients(
    val name: String,
    val amount: Double,
    val unit: String
)

data class CaloricBreakdown(
    val percentProtein: Double,
    val percentFat: Double,
    val percentCarbs: Double
)

data class Calories(
    val calories: Double,
    val fat: String,
    val protein: String,
    val carbs: String
)

data class Image(
    val url: String
)