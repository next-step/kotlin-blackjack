package blackjack.view

object InputView {
    private const val INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val ASK_NEED_CARD_MESSAGE = "는 한장의 카드를 더 받으시겠습니까? (예는 y, 아니오는 n)"

    fun readPlayerNames(): List<String> {
        println(INPUT_PLAYER_NAMES_MESSAGE)
        return readln().split(",")
    }

    fun askNeedCard(playerName: String): Boolean {
        println(playerName + ASK_NEED_CARD_MESSAGE)
        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException()
        }
    }
}
