package blackjack.domain

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination.Companion.BLACKJACK_COUNT

class Player(val name: String) {
    val cards = mutableListOf<Card>()
    val total get() = cards.sortedBy { it.denomination }.fold(0) { total, card -> total + card.getScore(total) }
    private val isBust get() = total > BLACKJACK_COUNT
    var isStay = false

    fun addCard(card: Card): List<Card> {
        cards.add(card)
        return cards
    }

    fun addCards(card: List<Card>): List<Card> {
        cards.addAll(card)
        return cards
    }

    fun hit(): Boolean {
        return if (isBust) false
        else {
            addCard(Deck.poll())
            true
        }
    }

    fun stay(): Boolean {
        isStay = true
        return isStay
    }

    fun isGaming(): Boolean {
        return !isBust && !isStay
    }

    fun choose(yOrN: String): Boolean {
        return if (yOrN == "y") {
            hit()
        } else {
            isStay = true
            false
        }
    }
}
