package blackjack.view

object BlackjackInputView {
    private const val PLAYER_NAME_DELIMITER = ","

    fun readPlayerNamesInput(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return readln().split(PLAYER_NAME_DELIMITER)
            .map { it.trim() }
    }
}
