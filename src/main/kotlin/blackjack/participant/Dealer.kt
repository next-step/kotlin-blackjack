package blackjack.participant

import blackjack.card.Card
import blackjack.card.Cards

data class Dealer(
    override val name: PlayerName = PlayerName(DEALER_NAME),
    var cards: Cards = Cards(),
) : Participant(name) {

    override fun take(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    override fun score(): Int {
        return cards.bestScore()
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}