package ru.mtrefelov.dialer.contacts.presenter

import ru.mtrefelov.dialer.contacts.MainContract
import ru.mtrefelov.dialer.contacts.model.*

class MainPresenter(view: MainContract.View, contactsJsonArray: String) : MainContract.Presenter {
    private val repository = ContactRepository(contactsJsonArray)
    private var view: MainContract.View? = view

    override fun onViewCreated() {
        view?.run {
            val contacts = repository.findAll()
            setContacts(contacts)
        }
    }

    override fun onSearchButtonClicked() {
        view?.run {
            val query = getSearchQuery()
            val contacts = repository.findByOccurrence(query)
            setContacts(contacts)
        }
    }

    override fun onDestroy() {
        view = null
    }
}