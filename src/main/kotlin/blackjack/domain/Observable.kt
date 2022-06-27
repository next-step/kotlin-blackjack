package blackjack.domain

class Observable<T>(value: T) {
    var value: T = value
        set(value) {
            field = value
            observer?.invoke(field)
        }

    private var observer: ((T) -> Unit)? = null

    fun observe(observer: (T) -> Unit) {
        this.observer = observer
    }
}
