package ru.mtrefelov.dialer.contacts.model

class Contact(val name: String, val phone: String, val type: String) {
    fun containsText(text: CharSequence): Boolean {
        val ignoreCase = true
        return name.contains(text, ignoreCase) || type.contains(text, ignoreCase)
    }
}
