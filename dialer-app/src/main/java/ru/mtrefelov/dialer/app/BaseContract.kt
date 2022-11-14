package ru.mtrefelov.dialer.app

interface BaseContract {
    interface Presenter {
        fun destroy()
    }

    interface View<T : Presenter> {
        fun setPresenter(presenter: T)
    }
}