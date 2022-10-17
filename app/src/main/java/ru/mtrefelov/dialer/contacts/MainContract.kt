package ru.mtrefelov.dialer.contacts

import ru.mtrefelov.dialer.base.*
import ru.mtrefelov.dialer.contacts.model.Contact

interface MainContract {
    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun onSearchButtonClicked()
    }

    interface View : BaseView<Presenter> {
        fun getSearchQuery(): CharSequence
        fun setContacts(contacts: Collection<Contact>)
    }
}