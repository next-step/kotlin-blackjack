package blackjack.view.input.parser

class PlayerNamesInputParser : InputParser<List<String>> {
    override fun parseValue(inputString: String?): ParseResult<List<String>> {
        val nameList = inputString?.split(",")
            ?.map { it.trim() }
            ?.filter { it.isNotBlank() }
            ?.distinct() ?: return ParseResult.Error(ERROR_MESSAGE_INVALID_NAME)

        if (nameList.count() < 2) {
            return ParseResult.Error(ERROR_MESSAGE_NOT_ENOUGH_MEMBER)
        }
        return ParseResult.Value(nameList)
    }

    companion object {
        private const val ERROR_MESSAGE_INVALID_NAME = "잘못된 이름 입니다."
        private const val ERROR_MESSAGE_NOT_ENOUGH_MEMBER = "2명 이상 입력해 주세요"
    }
}
