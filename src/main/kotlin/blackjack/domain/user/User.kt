package blackjack.domain.user

import blackjack.domain.Card
import blackjack.domain.Score

abstract class User {

    abstract val name: String

    protected val cards: MutableList<Card> = mutableListOf()

    fun cards(): List<Card> {
        return cards.toList()
    }

    fun sizeOfCards(): Int {
        return cards.size
    }

    fun isEmptyCards(): Boolean {
        return cards.isEmpty()
    }

    fun addCards(cards: List<Card>) {
        cards.forEach(this::addCard)
    }

    abstract fun addCard(card: Card)

    abstract fun drawable(): Boolean

    fun getScore(): Score {
        return Score(cards)
    }
}
