package ui.result

import blackjack.deck.Deck
import blackjack.player.Player
import toUiString

class ResultView {

    private fun showCardsForPlayer(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ") { it.toUiString() }}")
    }

    fun showInitialCards(deck: Deck, players: List<Player>): List<Player> {
        val resultPlayers = players.take(2).map { it.drawCard(deck).drawCard(deck) }

        println("${resultPlayers.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        resultPlayers.forEach(this::showCardsForPlayer)

        return resultPlayers
    }

    fun showHandCards(player: Player) {
        showCardsForPlayer(player)
    }

    fun showFinalResults(players: List<Player>) {
        players.forEach { player ->
            println("${player.name}카드: ${player.cards.joinToString(", ") { it.toUiString() }} - 결과: ${player.calculateBestValue()}")
        }
    }
}
