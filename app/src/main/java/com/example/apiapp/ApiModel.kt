package com.example.apiapp

data class ApiModel(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<DataModel>,
    val support: SupportModel

)

data class DataModel(

    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String

)

data class SupportModel(
    val url: String,
    val text: String
)