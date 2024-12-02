package blackjack.view

object InputView {
    private const val MESSAGE_INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val MESSAGE_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val YES = "y"
    private const val NO = "n"

    fun inputPlayerNames(): List<String> {
        println(MESSAGE_INPUT_PLAYER_NAMES)
        return requireNotNull(readln().split(",").map { it.trim() })
    }

    fun inputMoreCard(playerName: String): Boolean {
        println(MESSAGE_MORE_CARD.format(playerName))
        val input = readln()
        require(input in setOf(YES, NO))
        return input == YES
    }
}
