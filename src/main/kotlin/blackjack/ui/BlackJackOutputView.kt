package blackjack.ui

import blackjack.domain.player.Player
import blackjack.domain.trump.Card

object BlackJackOutputView {

    fun showPlayersCards(players: List<Player>) {
        players.forEach { println(getPlayersCards(it)) }
    }

    fun showGameResult(players: List<Player>) {
        players.forEach {println("${getPlayersCards(it)} - 결과: ${it.score()}")}
    }

    private fun getPlayersCards(player: Player): String {
        return "${player.name.value}카드: " + player.cards.values.joinToString(",") { descriptionOfCard(it) }
    }

    private fun descriptionOfCard(card: Card): String {
        return card.number.symbol + card.pattern.symbol
    }
}
