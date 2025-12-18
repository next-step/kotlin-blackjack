package domain

import domain.BlackJackConstants.Companion.ACE_GAP
import domain.BlackJackConstants.Companion.BLACKJACK_WIN_SCORE

class Cards(cards: List<Card> = mutableListOf<Card>()) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    override fun toString(): String {
        return _cards.joinToString()
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun isBust(): Boolean {
        return calculateScore() > BLACKJACK_WIN_SCORE
    }

    /**
     * 카드 합산 점수 계산
     * A는 1 또는 11로 계산, 1로 계산했을 때 21을 초과하지 않으면 10으로 계산
     */
    fun calculateScore(): Int {
        var total = 0
        var aceCount = 0

        for (card in _cards) {
            total += card.rank.value
            if (card.isAce()) {
                aceCount++
            }
        }

        while (total + ACE_GAP <= BLACKJACK_WIN_SCORE && aceCount > 0) {
            total += ACE_GAP
            aceCount--
        }

        return total
    }
}
