package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.deck.Card.Companion.ACE_MIN_MAX_POINT_GAP
import kotlin.math.roundToInt

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

    fun getProfit(isWin: Boolean, rate: Double): Int {
        if (isWin) {
            return (bettingMoney * rate).roundToInt()
        }
        return bettingMoney.unaryMinus()
    }

    fun isBlackJack(): Boolean {
        return cards.size == 2 && score() == 21
    }

    companion object {
        //TODO 위치 변경 필요
        const val MAX_SCORE = 21
    }
}
