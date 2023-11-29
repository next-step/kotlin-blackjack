package blackjack.view

import blackjack.domain.player.Players

object OutputView {

    private const val DRAW_CARD_MESSAGE_FORMAT = "%s에게 %d장의 카드를 나누었습니다."
    private const val PLAYER_CARD_MESSAGE_FORMAT = "%s카드: %s"

    fun drawCardMessage(players: Players, initDrawCardCount: Int) {
        val playerNames = players.values.joinToString(", ") { it.name.value }
        println(String.format(DRAW_CARD_MESSAGE_FORMAT, playerNames, initDrawCardCount))
    }

    fun playerCardMessage(players: Players) {
        players.values.forEach { player ->
            val playerName = player.name.value
            val playerCards = player.cards.values.joinToString(", ") {
                CardViewCreator.convert(it)
            }
            println(String.format(PLAYER_CARD_MESSAGE_FORMAT, playerName, playerCards))
        }
    }
}
