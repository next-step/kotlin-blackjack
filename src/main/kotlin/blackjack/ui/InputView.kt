package blackjack.ui

object InputView {
    private const val PLAYER_NAMES_PROMPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val COMMA_DELIMITER = ","

    fun getPlayerNames(): List<String> {
        println(PLAYER_NAMES_PROMPT)
        return readln().split(COMMA_DELIMITER).map { it.trim() }
    }
}
