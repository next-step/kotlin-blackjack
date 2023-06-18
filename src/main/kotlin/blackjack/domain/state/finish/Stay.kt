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
        val bonusScore = score + denomination.bonus - denomination.score

        return if (bonusScore > BUST_LIMIT_SCORE) {
            score
        } else {
            bonusScore
        }
    }

    companion object {
        private const val BUST_LIMIT_SCORE: Int = 21
    }
}
