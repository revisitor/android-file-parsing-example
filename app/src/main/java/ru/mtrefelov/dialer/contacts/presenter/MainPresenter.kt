package ru.mtrefelov.dialer.contacts.presenter

import ru.mtrefelov.dialer.contacts.MainContract
import ru.mtrefelov.dialer.contacts.model.*

class MainPresenter(view: MainContract.View, contactsJsonArray: String) : MainContract.Presenter {
    private val repository = ContactRepository(contactsJsonArray)
    private var view: MainContract.View? = view

    override fun onViewCreated() {
        val contacts: Collection<Contact> = repository.findAll()
        view?.setContacts(contacts)
    }

    override fun onSearchButtonClicked() {
        val query: CharSequence? = view?.getSearchQuery()
        query?.let {
            val contacts: Collection<Contact> = repository.findByOccurrence(it)
            view?.setContacts(contacts)
        }
    }

    override fun onDestroy() {
        view = null
    }
}