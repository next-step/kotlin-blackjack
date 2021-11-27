package blackjack.domain

import blackjack.domain.exceptions.ScoreOverflowException
import blackjack.domain.extensions.deepCopy

open class Player(val name: String) {

    init {
        require(!name.isNullOrBlank()) { INVALID_NAME_ERROR_MSG }
    }

    private val normalCards: MutableList<Card> = mutableListOf()
    private val aceCards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = (normalCards + aceCards).deepCopy()

    open val canTakeCards: Boolean
        get() = getTotalScore() < BLACKJACK_NUMBER

    fun takeCards(cards: List<Card>) {
        if (!canTakeCards) {
            throw ScoreOverflowException(SCORE_OVERFLOW_ERROR_MSG)
        }

        cards.forEach {
            when (it.number.value) {
                ACE_NUMBER -> aceCards.add(it)
                else -> normalCards.add(it)
            }
        }
    }

    fun getTotalScore(): Int {
        var totalScore = 0
        normalCards.forEach { totalScore += it.number.value }
        aceCards.forEach { _ ->
            totalScore += if (totalScore + ACE_NUMBER_ALT > BLACKJACK_NUMBER) ACE_NUMBER else ACE_NUMBER_ALT
        }
        return totalScore
    }

    companion object {
        protected const val BLACKJACK_NUMBER = 21
        private const val ACE_NUMBER = 1
        private const val ACE_NUMBER_ALT = 11
        private const val INVALID_NAME_ERROR_MSG = "잘못된 이름입니다."
        private const val SCORE_OVERFLOW_ERROR_MSG = "이미 최대 점수를 넘어섰습니다. 카드를 더이상 받을 수 없습니다."
    }
}
