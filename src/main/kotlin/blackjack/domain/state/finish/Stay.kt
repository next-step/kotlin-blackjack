package blackjack.domain.state.finish

import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Denomination
import blackjack.domain.state.FinishState

class Stay(playingCards: PlayingCards) : FinishState(playingCards = playingCards) {

    override fun resultScore(): Int {
        val ace = Denomination.ACE

        return when {
            playingCards.includeDenomination(denomination = ace) -> {
                calculateOptimalScore(playingCards = playingCards, denomination = ace)
            }

            else -> playingCards.sumScore()
        }
    }

    private fun calculateOptimalScore(playingCards: PlayingCards, denomination: Denomination): Int {
        val score = playingCards.sumScore()
        val bonusScore = score + denomination.bonus

        return if (bonusScore > PlayingCards.ALLOWABLE_MAXIMUM_SCORE) {
            score
        } else {
            bonusScore
        }
    }
}
