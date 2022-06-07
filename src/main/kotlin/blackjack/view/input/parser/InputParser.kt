package blackjack.view.input.parser

interface InputParser<T> {
    fun parseValue(inputString: String?): ParseResult<T>
}
