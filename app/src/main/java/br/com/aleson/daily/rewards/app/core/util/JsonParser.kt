package br.com.aleson.daily.rewards.app.core.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

class JsonParser {

    private val gson: Gson = GsonBuilder().create()

    fun <T> parseJsonToObject(json: String, clazz: Class<T>) : T{
        return gson.fromJson<T>(json, clazz)
    }

    fun <T> parseJsonToObject(json: String, type: Type) : T {
        return gson.fromJson<T>(json, type)
    }

    fun parseObjectToJson(obj: Any): String {
        return gson.toJson(obj)
    }
}