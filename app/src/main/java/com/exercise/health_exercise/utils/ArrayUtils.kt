package com.exercise.health_exercise.utils

open class ArrayUtils {
    open fun <T> hasValue(arr: Collection<T>?): Boolean {
        return arr != null && arr.size > 0
    }

    open fun <T> count(arr: Collection<T>): Int {
        return if (!hasValue(arr)) 0 else arr.size
    }
}