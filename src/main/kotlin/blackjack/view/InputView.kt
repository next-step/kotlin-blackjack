package blackjack.view

object InputView {
    private const val PLAYER_NAMES_QUESTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

    fun getPlayerNames(): List<String> {
        println(PLAYER_NAMES_QUESTION)

        return readln()
            .split(",")
            .toNonBlankStrings()
            .throwExceptionIfEmpty()
    }

    private fun List<String>.toNonBlankStrings(): List<String> = map { it.trim() }
        .filter { it.isNotBlank() }

    private fun List<String>.throwExceptionIfEmpty(): List<String> = takeUnless { it.isEmpty() }
        ?: throw IllegalArgumentException("최소 1개 이상의 값을 입력해주세요.")
}
