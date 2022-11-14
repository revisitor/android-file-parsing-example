package ru.mtrefelov.dialer.app.di

import android.content.Context

import com.google.gson.Gson

import ru.mtrefelov.dialer.R
import ru.mtrefelov.dialer.core.*
import ru.mtrefelov.dialer.data.ContactsDummyRepository

class DependencyContainer(applicationContext: Context) {
    val contactsRepository: ContactsRepository = with(applicationContext) {
        val contactsJson: String = resources.openRawResource(R.raw.data).use { inputStream ->
            inputStream.bufferedReader().use { reader ->
                reader.readText()
            }
        }

        val contacts = Gson()
            .fromJson(contactsJson, Array<Contact>::class.java)
            .toList()

        ContactsDummyRepository(contacts)
    }
}