package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.game.ACE_HIDDEN_VALUE
import blackjack.domain.game.BLACKJACK_NUMBER

class Hands(private val cards: MutableSet<Card> = mutableSetOf()) {

    fun getCards(): Set<Card> = cards.toSet()
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return cards.sumOf { it.getValue() } > BLACKJACK_NUMBER
    }

    fun getCardsValue(): Int {
        val total = cards.sumOf { it.getValue() }
        if (hasAce() && total + ACE_HIDDEN_VALUE <= BLACKJACK_NUMBER) {
            return total + ACE_HIDDEN_VALUE
        }
        return total
    }

    private fun hasAce(): Boolean {
        return cards.firstOrNull { it.isAce() }.let { true }
    }
}
