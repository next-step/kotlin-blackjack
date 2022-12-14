package blackjack.view

import blackjack.domain.Deck.Companion.INITIAL_CARD_COUNT
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.Players

object ConsoleOutput {
    fun printInitialCards(game: Game) {
        println("달러와 ${game.players.list.joinToString { it.name.value }}에게 ${INITIAL_CARD_COUNT}장의 카드를 나누었습니다.")
        printPlayerCards(game.getDealer())
        game.players.list.map { printPlayerCards(it) }
        println()
    }

    fun printPlayerCards(player: Player) = println(getPlayerInfo(player))

    fun printGameResult(dealer: Player, players: Players) {
        printGameResult(dealer)
        players.list.map { printGameResult(it) }
    }

    private fun printGameResult(player: Player) {
        println("${getPlayerInfo(player)} - 결과: ${player.countingCard()}")
    }

    private fun getPlayerInfo(player: Player) = "${player.name.value} 카드: ${player.cards}"
}
