package kz.test.lab2.model.entity

data class Person(
    val name: String,
    val title: String? = null,
    val info: Info? = null
)

