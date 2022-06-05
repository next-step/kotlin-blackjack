package blackjack.view.inputconverter

interface InputConverter<T> {
    fun convert(input: String): T
}
