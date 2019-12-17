package com.kotlintest.reified

import com.google.gson.Gson

inline fun  <K, reified T> HashMap<K, T>.plusOrDefault(key: K, plus: T, defaultValue: T){
    if(!containsKey(key)){
        put(key, defaultValue)
    }
    val value: T? = this.get(key)
    var val2: T?=null
    when(get(key)){
        is Int -> {
            val2 = ((value as? Int)?.plus(plus as Int) ?: put(key, defaultValue)) as T
        }
        is Long -> {
            val2 = ((value as? Long)?.plus(plus as Long) ?: put(key, defaultValue)) as T
        }
        is Float -> {
            val2 = ((value as? Float)?.plus(plus as Float) ?: put(key, defaultValue)) as T
        }
        is Double -> {
            val2 = ((value as? Double)?.plus(plus as Double) ?: put(key, defaultValue)) as T
        }
    }
    val2?.let {it-> this.put(key, it) }

}

inline fun  <K, T> HashMap<K, T>.plusOrDefault2(key: K, plus: T, defaultValue: T){
    if(!containsKey(key)){
        put(key, defaultValue)
    }
    val value: T? = this.get(key)
    var val2: T?=null
    when(get(key)){
        is Int -> {
            val2 = ((value as? Int)?.plus(plus as Int) ?: put(key, defaultValue)) as T  //這樣寫會有warning...
        }
        is Long -> {
            val2 = ((value as? Long)?.plus(plus as Long) ?: put(key, defaultValue)) as T  //這樣寫會有warning..
        }
        is Float -> {
            val2 = ((value as? Float)?.plus(plus as Float) ?: put(key, defaultValue)) as T  //這樣寫會有warning..
        }
        is Double -> {
            val2 = ((value as? Double)?.plus(plus as Double) ?: put(key, defaultValue)) as T  //這樣寫會有warning..
        }
    }
    val2?.let {it-> this.put(key, it) }

}

class Michael{
    var hitMichaelCount: Int=0
    init {
        println("init michael")
    }
}

/**
 * 這樣寫不行
 */
fun <T> createMichael(): T?{
    return T::class.java.newInstance()
    //return null
}

inline fun <reified T> createMichaelEx(): T{
    return T::class.java.newInstance()
}

inline fun <reified T> toObject(json: String): T{
    return Gson().fromJson(json, T::class.java)
}

inline fun <reified T> toJson(json: T): String{
    return Gson().toJson(json, T::class.java)
}


fun main(args: Array<String>) {

    val m = HashMap<Int, Int>()
    m.plusOrDefault(9, 2, 1)
    println()
    val michael = createMichaelEx<Michael>()
    michael.hitMichaelCount = 999
    val json = toJson<Michael>(michael)
    println("toJson $json")
    val michaelClone = toObject<Michael>(json)
    println("toObject $michaelClone")
}
