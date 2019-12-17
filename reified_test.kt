package com.kotlintest.reified

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

class Michael{
    init {
        println("init michael")
    }
}

class Michael1{

}

fun <T> createMichael(): T{
    return T::class.java.newInstance()
}

inline fun <reified T> createMichaelEx(): T{
    return T::class.java.newInstance()
}

inline fun <reified T> toObject(json: String, clz: T): T{

}


fun main(args: Array<String>) {

    val m = HashMap<Int, Int>()
    m.plusOrDefault(9, 2, 1)
    println()
    createMichaelEx<Michael>()
}
