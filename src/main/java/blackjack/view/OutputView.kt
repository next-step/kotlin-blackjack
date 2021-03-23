package blackjack.view

import blackjack.Player
import blackjack.card.Card

object OutputView {

    fun showCard(player: Player) {
        println("${player.name}카드: ${getCardNames(player.cards)}")
    }

    fun showResult(player: Player, sum: Int) {
        println("${player.name}카드: ${getCardNames(player.cards)} - 결과: $sum")
    }

    private fun getCardNames(cards: List<Card>): String {
        return cards.joinToString { "${it.cardValue.desc}${it.cardType.desc}" }
    }
}
