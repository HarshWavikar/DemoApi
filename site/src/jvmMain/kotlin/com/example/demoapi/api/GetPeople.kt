package com.example.demoapi.api

import com.example.demoapi.models.ApiResponse
import com.example.demoapi.models.Person
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

private val people = listOf(
    Person(name = "Harsh", age = 29),
    Person(name = "Jeena", age = 23),
    Person(name = "Luke", age = 1),
    Person(name = "Sharon", age = 21),
    Person(name = "Kishor", age = 62),
    Person(name = "Sandhya", age = 55),
)

@Api
suspend fun getPeople(context: ApiContext) {
    try {
        val number = context.req.params.getValue("count").toInt()
        context.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Success(data = people.take(number))
            )
        )
    } catch (e: Exception) {
        context.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Error(errorMessage = e.message.toString())
            )
        )
    }
}