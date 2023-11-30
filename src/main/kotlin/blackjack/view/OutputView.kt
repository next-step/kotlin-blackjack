package blackjack.view

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerCards
import blackjack.domain.player.Players
import blackjack.domain.player.forEach

object OutputView {

    private const val DRAW_CARD_MESSAGE_FORMAT = "%s에게 %d장의 카드를 나누었습니다."
    private const val PLAYER_CARD_MESSAGE_FORMAT = "%s카드: %s"
    private const val BUST_MESSAGE = "%s는 점수 총합 %d으로, 버스트 되었습니다."
    private const val RESULT_MESSAGE = "%s카드: %s - 결과: %d"

    fun drawCardMessage(players: Players, initDrawCardCount: Int) {
        val playerNames = players.values.joinToString(", ") { it.name.value }
        println(String.format(System.lineSeparator() + DRAW_CARD_MESSAGE_FORMAT, playerNames, initDrawCardCount))
    }

    fun playerCardMessage(player: Player) {
        val playerName = player.name.value
        val playerCards = convertPlayerCardMessage(player.cards)
        println(String.format(PLAYER_CARD_MESSAGE_FORMAT, playerName, playerCards))
    }

    fun bustMessage(player: Player) {
        println(String.format(BUST_MESSAGE, player.name.value, player.calculateScore().value))
    }

    fun printResult(players: Players) {
        print(System.lineSeparator())
        players.forEach { player ->
            val playerName = player.name.value
            val playerCards = convertPlayerCardMessage(player.cards)
            val playerScore = player.calculateScore().value
            println(String.format(RESULT_MESSAGE, playerName, playerCards, playerScore))
        }
    }

    private fun convertPlayerCardMessage(cards: PlayerCards): String {
        return cards.values.joinToString(", ") {
            CardViewCreator.convert(it)
        }
    }
}
