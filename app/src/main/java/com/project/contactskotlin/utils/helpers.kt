package com.project.contactskotlin.utils


fun validateName(name: String): Boolean {
    if (name == "" || name.contains("[0-9]".toRegex())) return false
    return true
}

fun validatePhone(name: String): Boolean {
    if (name.contains("[0-9]".toRegex()) && name.length >= 6) return true
    return false
}