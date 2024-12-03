package kz.test.lab2.model.entity

data class ApiResponse(
    val backUrl: String,
    val data: List<Data>,
    val message: String,
    val status: String
)

data class Data(
    val thumbnail: String,
    val title: String,
    val videoId: String,
    val audioUrl: String?
)