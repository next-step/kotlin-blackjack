package blackjack.participant

import blackjack.card.Card
import blackjack.card.Cards

data class Dealer(
    private val name: PlayerName = PlayerName(DEALER_NAME),
    var cards: Cards = Cards(),
) : Participant(name) {

    fun take(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    fun score(): Int {
        return cards.bestScore()
    }

    override fun toString(): String {
        return "${name.value}카드: $cards"
    }

    fun isMoreCard(): Boolean {
        return score() < MORE_CARD_CONDITION_SCORE
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val MORE_CARD_CONDITION_SCORE = 17
    }
}