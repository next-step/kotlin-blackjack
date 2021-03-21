package blackjack.view.converter

interface ResultStringConverter<T> {
    fun convert(t: T): String
}
