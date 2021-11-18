package blackjack.domain

import blackjack.domain.extensions.deepCopy

class Player(val name: String) {

    init {
        require(!name.isNullOrBlank()) { INVALID_NAME_ERROR_MSG }
    }

    private val normalCards: MutableList<Card> = mutableListOf()
    private val courtCards: MutableList<Card> = mutableListOf()
    private val aceCards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = (normalCards + aceCards + courtCards).deepCopy()

    val canTakeCards: Boolean
        get() = getTotalScore() < BLACKJACK_NUMBER

    fun takeCards(cards: List<Card>) {
        if (!canTakeCards) {
            return
        }

        cards.forEach {
            when {
                it.number.value == ACE_NUMBER -> aceCards.add(it)
                it.number.value > MAX_NUMBER -> courtCards.add(it)
                else -> normalCards.add(it)
            }
        }
    }

    fun getTotalScore(): Int {
        var totalScore = 0
        normalCards.forEach { totalScore += it.number.value }
        courtCards.forEach { _ -> totalScore += MAX_NUMBER }
        aceCards.forEach {
            totalScore += if (totalScore + ACE_NUMBER_ALT > BLACKJACK_NUMBER) ACE_NUMBER else ACE_NUMBER_ALT
        }
        return totalScore
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val ACE_NUMBER = 1
        private const val ACE_NUMBER_ALT = 11
        private const val MAX_NUMBER = 10
        private const val INVALID_NAME_ERROR_MSG = "잘못된 이름입니다."
    }
}
