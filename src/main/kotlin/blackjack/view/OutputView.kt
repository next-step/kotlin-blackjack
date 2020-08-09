package blackjack.view

import blackjack.model.Player
import blackjack.model.card.Card

object OutputView {
    fun firstTurn(players: List<Player>) {
        val name = players.joinToString(separator = ",") { it.name }

        println("${name}에게 2장의 카드를 나누었습니다.")
    }

    fun drawCard(player: Player) {
        val cards = displayCards(player.cards)

        println("${player.name}카드: $cards")
    }

    private fun displayCards(cards: List<Card>): String {
        return cards.joinToString(separator = ",") { "${it.denomination.title}${it.suit.title}" }
    }
}
