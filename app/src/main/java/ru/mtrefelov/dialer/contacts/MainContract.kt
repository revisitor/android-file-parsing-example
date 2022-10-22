package ru.mtrefelov.dialer.contacts

import ru.mtrefelov.dialer.base.*
import ru.mtrefelov.dialer.contacts.model.Contact

interface MainContract {
    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun onSearchQueryChanged()
    }

    interface View : BaseView<Presenter> {
        fun getSearchQuery(): CharSequence
        fun setContacts(contacts: List<Contact>)
        fun openDialer(contact: Contact)
    }
}