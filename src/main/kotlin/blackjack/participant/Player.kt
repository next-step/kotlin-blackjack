package blackjack.participant

import blackjack.card.Card
import blackjack.card.Cards

data class Player(
    override val name: PlayerName,
    var cards: Cards = Cards(),
) : Participant(name) {
    fun isNotBust(): Boolean {
        return cards.isBust().not()
    }

    override fun take(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    override fun score(): Int {
        return cards.bestScore()
    }

    override fun toString(): String {
        return "${name.value}카드: $cards"
    }
}
