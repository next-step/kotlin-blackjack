package blackjack.model

import blackjack.model.score.Score
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Cards

object BlackJackRule {
    fun getScore(cards: Cards): Score {
        return findValidScore(cards.getHighestScore(), cards.countAce())
    }

    private fun findValidScore(score: Score, aceCount: Int): Score {
        var scoreVar = score
        var aceCountVar = aceCount

        while (!scoreVar.isValid() && aceCountVar > 0) {
            scoreVar -= CardNumber.ACE.diffHighestAndLowest()
            aceCountVar--
        }

        return if (scoreVar.isValid()) scoreVar else Score.ZERO
    }
}
