package blackjack.view.input.parser

import java.util.Locale

class YesNoInputParser() : InputParser<Boolean> {

    override fun parseValue(inputString: String?): ParseResult<Boolean> {

        return when (inputString?.uppercase(Locale.getDefault())) {
            "Y" -> ParseResult.Value(true)
            "N" -> ParseResult.Value(false)
            else -> ParseResult.Error(MESSAGE_ERROR)
        }
    }

    private companion object {
        private const val MESSAGE_ERROR = "y 또는 n으로 입력해 주세요"
    }
}
