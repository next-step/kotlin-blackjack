package blackjack.view.input

import blackjack.view.input.parser.InputParser
import blackjack.view.input.parser.ParseResult

object ConsoleReader {

    tailrec fun <T> read(message: String, inputParser: InputParser<T>): T {
        if (message.isNotEmpty()) {
            println(message)
        }

        return when (val parsedValue = this.tryToRead(inputParser)) {
            is ParseResult.Value -> parsedValue.value
            is ParseResult.Error -> {
                println(parsedValue.error.message ?: "")
                read(message, inputParser)
            }
        }
    }

    private fun <T> tryToRead(inputParser: InputParser<T>): ParseResult<T> =
        parseString(readLine(), inputParser)

    private fun <T> parseString(inputString: String?, inputParser: InputParser<T>): ParseResult<T> =
        if (inputString.isNullOrBlank()) {
            ParseResult.Error("빈 문자열이 입력되었습니다.")
        } else {
            inputParser.parseValue(inputString)
        }
}
