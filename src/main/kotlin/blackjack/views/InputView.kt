package blackjack.views

import blackjack.domain.Player

class InputView {

    fun receivePlayers(): List<Player> {
        println(RECEIVE_PLAYERS_MSG)
        val input = readLine()
        require(!input.isNullOrBlank()) { EMPTY_STRING_ERROR_MSG }
        return input
            .filter { !it.isWhitespace() }
            .split(",")
            .map { Player(it) }
    }

    fun receiveCardDrawDecision(player: Player): Boolean {
        println(RECEIVE_ANOTHER_CARD_MSG.format(player.name))
        val input = readLine()
        require(!input.isNullOrBlank()) { EMPTY_STRING_ERROR_MSG }
        return when (input) {
            YES -> true
            NO -> false
            else -> throw IllegalArgumentException(INVALID_INPUT_ERROR_MSG)
        }
    }

    companion object {
        private const val RECEIVE_PLAYERS_MSG = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val YES = "y"
        private const val NO = "n"
        private const val RECEIVE_ANOTHER_CARD_MSG = "%s 는 한장의 카드를 더 받겠습니까?(예는 $YES, 아니오는 $NO)"
        private const val EMPTY_STRING_ERROR_MSG = "입력이 비어있습니다."
        private const val INVALID_INPUT_ERROR_MSG = "잘못된 입력입니다."
    }
}
