package blackjack.participant

import blackjack.card.Card
import blackjack.card.Cards

data class Player(
    val name: PlayerName,
    var cards: Cards = Cards(),
) {
    fun take(newCard: Card) {
        cards.add(newCard)
    }

    fun take(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    fun isNotBust(): Boolean {
        return cards.isBust().not()
    }

    fun score(): Int {
        return cards.bestScore()
    }

    override fun toString(): String {
        return "${name.value}카드: $cards"
    }
}
