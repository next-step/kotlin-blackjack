package blackjack.model

import blackjack.model.score.Score
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Cards

object BlackJackRule : Rule {
    override fun getScore(cards: Cards): Score {
        var score = cards.getHighestScore()

        if (score.isMaximum()) {
            return Score(score.value, true)
        }

        return findValidScore(score, cards.countAce())
    }

    private fun findValidScore(score: Score, aceCount: Int): Score {
        var scoreVar = score
        var aceCountVar = aceCount

        while (!scoreVar.isValid() && aceCountVar > 0) {
            scoreVar -= CardNumber.ACE.diffHighestAndLowest()
            aceCountVar--
        }

        return scoreVar
    }
}
