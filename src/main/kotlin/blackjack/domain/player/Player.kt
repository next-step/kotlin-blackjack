package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.deck.Card.Companion.ACE_MIN_MAX_POINT_GAP

data class Player(val name: String, val bettingMoney: Int) {
    private val _cards = mutableListOf<Card>()
    private var existAce: Boolean = false

    val cards: List<Card>
        get() {
            return _cards.toList()
        }

    fun addCard(card: Card) {
        _cards.add(card)
        if (card.isAce()) {
            existAce = true
        }
    }

    fun score(): Int {
        if (existAce) {
            return sumScoreWithAce()
        }
        return sumDefaultScore()
    }

    private fun sumScoreWithAce(): Int {
        val defaultScore = sumDefaultScore()
        if (!isBust(defaultScore)) {
            return defaultScore
        }
        return defaultScore - ACE_MIN_MAX_POINT_GAP
    }

    fun isBust(): Boolean {
        return isBust(score())
    }

    private fun isBust(score: Int) = score > MAX_SCORE

    private fun sumDefaultScore() = cards.map { it.denomination.point }.sum()

    companion object {
        const val MAX_SCORE = 21
    }
}
