package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardType

data class Player(
    private val _name: String,
    val receivedCards: MutableSet<Card> = mutableSetOf(),
    private var _score: Int = 0
) {

    val score: Int
        get() = _score

    val name: String
        get() = _name

    fun calculateScore(): Int {
        _score = receivedCards.sumOf { it.number }

        if (_score > BLACKJACK_SCORE) {
            val aceCount = receivedCards.count {
                it.cardType == CardType.ACE
            }

            _score = _score - (ACE_NUMBER_TO_ELEVEN * aceCount) + (ACE_NUMBER_TO_ONE * aceCount)
        }

        return _score
    }

    fun canMoreGame(): Boolean {
        return _score != BLACKJACK_SCORE && _score < BLACKJACK_SCORE
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_NUMBER_TO_ONE = 1
        private const val ACE_NUMBER_TO_ELEVEN = 11
    }
}
