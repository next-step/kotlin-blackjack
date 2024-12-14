package blackjack.participant

import blackjack.card.Card
import blackjack.card.Cards

data class Dealer(
    val name: PlayerName = PlayerName(DEALER_NAME),
    var cards: Cards = Cards(),
) {
    fun take(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}