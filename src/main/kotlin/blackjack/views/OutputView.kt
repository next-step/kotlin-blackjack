package blackjack.views

import blackjack.domain.player.Player
import blackjack.domain.player.Players

object OutputView {

    fun printInitPhase(players: Players) {
        val initPhasePlayers = players.players
        val names = initPhasePlayers.joinToString() { it.getPlayerName().name.toString() }
        println()
        println("$names$PRINT_CARD_INIT_PHASE")
        printPlayersCards(initPhasePlayers)
        println()
    }

    fun printPlayingPhase(players: Players) {
        val playingPhasePlayers = players.players
        printCardWithPoint(playingPhasePlayers)
    }

    fun printCards(player: Player) {
        val (playerName, cardResult) = getCardResult(player)
        println("$playerName: $cardResult")
    }

    private fun printPlayersCards(players: List<Player>) {
        players.forEach {
            val (playerName, cardResult) = getCardResult(it)
            println("$playerName: $cardResult")
        }
    }

    private fun printCardWithPoint(players: List<Player>) {
        println()
        players.forEach {
            val (playerName, cardResult) = getCardResult(it)
            println("$playerName: $cardResult $PRINT_POINT_RESULT ${it.getHighestPoint()}")
        }
    }

    private fun getCardResult(player: Player): Pair<String?, StringBuilder> {
        val playerName = player.getPlayerName().name
        val playerCards = player.openCards().cards
        val cardResult = StringBuilder()
        cardResult.apply {
            append(playerCards.joinToString() { it.denomination.mark + it.suit.value })
        }
        return Pair(playerName, cardResult)
    }

    private const val PRINT_CARD_INIT_PHASE = "에게 2장을 나누었습니다."
    private const val PRINT_POINT_RESULT = "- 결과:"
}
