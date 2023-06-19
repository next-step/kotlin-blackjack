package blackjack.domain.card

import blackjack.domain.deck.Denomination
import blackjack.domain.game.BlackjackGame

class PlayingCards(private val cards: MutableSet<Card> = mutableSetOf()) : Set<Card> by cards {

    fun add(card: Card) {
        check(value = card !in cards) {
            "중복된 카드를 추가할 수 없습니다. 카드 : $card"
        }

        cards.add(element = card)
    }

    fun isBust(): Boolean = sumScore() > ALLOWABLE_MAXIMUM_SCORE

    fun isBlackjack(): Boolean =
        calculateAceOptimalScore() == ALLOWABLE_MAXIMUM_SCORE && cards.size == BlackjackGame.INIT_HAND_COUNT

    fun calculateAceOptimalScore(): Int {
        val ace = Denomination.ACE

        return when {
            cards.any { it.denomination == ace } -> {
                calculateOptimalScore(denomination = ace)
            }

            else -> sumScore()
        }
    }

    private fun calculateOptimalScore(denomination: Denomination): Int {
        val score = sumScore()
        val bonusScore = score + denomination.bonus

        return if (bonusScore > ALLOWABLE_MAXIMUM_SCORE) {
            score
        } else {
            bonusScore
        }
    }

    private fun sumScore(): Int = cards.sumOf { it.denomination.score }

    companion object {
        private const val ALLOWABLE_MAXIMUM_SCORE: Int = 21
    }
}
