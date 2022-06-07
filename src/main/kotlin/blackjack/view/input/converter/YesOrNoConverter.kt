package blackjack.view.input.converter

object YesOrNoConverter : InputConverter<Boolean> {
    private const val YES = "y"
    private const val NO = "n"
    private const val MESSAGE_INVALID_INPUT_EXCEPTION = "y 또는 n이 입력되어야 합니다."

    override fun convert(input: String): Boolean {
        require(input == YES || input == NO) {
            MESSAGE_INVALID_INPUT_EXCEPTION
        }

        return input == YES
    }
}
