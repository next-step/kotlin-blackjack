package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardDeck

class InputView {
    fun getPlayers(): List<String> {
        println(INPUT_PLAYERS)
        val players = readln()
        println(players)

        val playerList = players.trim().split(SEPARATE_DELIMITER)
        check(playerList.size * STARTING_CARDS <= MAX_CARDS)
        return playerList
    }

    fun askThePlayer(player: String): Card? {
        println(String.format(INPUT_ASK_HIT, player))
        return when (readln()) {
            YES -> CardDeck.hit()
            NO -> null
            else -> throw IllegalArgumentException()
        }
    }

    companion object {
        private const val INPUT_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val INPUT_ASK_HIT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

        private const val YES = "y"
        private const val NO = "n"

        private const val SEPARATE_DELIMITER = ","

        private const val STARTING_CARDS = 2
        private const val MAX_CARDS = 52
    }
}
