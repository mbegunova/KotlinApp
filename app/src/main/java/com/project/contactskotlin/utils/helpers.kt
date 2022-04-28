package com.project.contactskotlin.utils

import java.util.*


fun validateName(name: String): Boolean {
    if (name == "" || name.contains("[0-9]".toRegex())) return false
    return true
}

fun validatePhone(name: String): Boolean {
    if (name.contains("[0-9]".toRegex()) && name.length >= 6) return true
    return false
}

fun containLowerSome(search: String, list: List<String>): Boolean {
    var contain = false;
    for (name in list) {
        if (name.lowercase().contains(search.lowercase())) {
            contain = true
            break;
        }
    }

    return contain;
}