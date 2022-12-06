package blackjack.domain

import blackjack.domain.Constants.BLACKJACK_SCORE

object Score {
    private const val SPECIAL_CARD_SCORE = 10
    private const val ACE_CARD_MIN_SCORE = 1
    private const val ACE_CARD_MAX_SCORE = 11

    fun countingCard(cards: Cards): Int {
        var score = 0
        cards.list.map {
            score += when (it.number) {
                CardNumber.ACE -> ACE_CARD_MIN_SCORE
                CardNumber.JACK -> SPECIAL_CARD_SCORE
                CardNumber.QUEEN -> SPECIAL_CARD_SCORE
                CardNumber.KING -> SPECIAL_CARD_SCORE
                else -> {
                    it.number.value.toInt()
                }
            }
        }
        score += countingMaxAceCard(score, cards.list.count { it.number == CardNumber.ACE })
        return score
    }

    private fun countingMaxAceCard(score: Int, countOfAceCard: Int): Int {
        if (countOfAceCard == 0) {
            return 0
        }

        var aceScore = 0
        repeat(countOfAceCard) {
            val tmp = score + aceScore - ACE_CARD_MIN_SCORE + ACE_CARD_MAX_SCORE
            if (tmp <= BLACKJACK_SCORE) {
                aceScore = ACE_CARD_MAX_SCORE - ACE_CARD_MIN_SCORE
            }
        }
        return aceScore
    }
}
