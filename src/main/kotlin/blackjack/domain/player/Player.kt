package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.score.Score

open class Player(val name: String, val bettingAmount: Int) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun score(): Int {
        return Score.calculate(this)
    }
}
