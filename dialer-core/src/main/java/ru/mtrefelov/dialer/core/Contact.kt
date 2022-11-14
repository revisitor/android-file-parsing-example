package ru.mtrefelov.dialer.core

data class Contact(val name: String, val phone: String, val type: String) {
    fun containsText(text: CharSequence): Boolean {
        return name.contains(text, true) || type.contains(text, true)
    }
}
