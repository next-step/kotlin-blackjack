package blackjack.domain

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination

class Player(val name: String) {
    val cards = mutableListOf<Card>()
    val total get() = cards.sortedBy { it.denomination }.fold(0) { total, card -> total + Denomination.scoreOf(card.denomination, total) }
    private val isBust get() = total > 21
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
            addCard(Deck.pop())
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
