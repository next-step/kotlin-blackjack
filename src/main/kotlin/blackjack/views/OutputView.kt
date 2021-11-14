package blackjack.views

import blackjack.domain.Player
import blackjack.domain.Players

object OutputView {

    fun printInitPhase(players: Players) {
        val initPhasePlayers = players.players
        val names = initPhasePlayers.joinToString() { it.getPlayerName().name.toString() }
        println("$names$PRINT_CARD_INIT_PHASE")
        printCards(initPhasePlayers)
    }

    fun printCards(players: List<Player>) {
        players.forEach { player ->
            val playerName = player.getPlayerName().name
            val playerCards = player.openCards().cards
            val stringBuilder = StringBuilder()
            stringBuilder.apply {
                append(playerCards.joinToString() { it.denomination.mark + it.suit.value })
            }
            println("$playerName: $stringBuilder")
        }
    }

    private const val PRINT_CARD_INIT_PHASE = "에게 2장을 나누었습니다."
}
