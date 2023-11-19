package blackjack.view

object BlackjackInputView {
    private const val PLAYER_NAME_DELIMITER = ","

    fun readPlayerNamesInput(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return readln().split(PLAYER_NAME_DELIMITER)
            .map { it.trim() }
    }

    fun readCardReceiveInput(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return parseCardReceiveInput(readln())
    }

    private fun parseCardReceiveInput(input: String): Boolean {
        return when (input) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("Input should be y or n.")
        }
    }
}
