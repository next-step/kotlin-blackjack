package blackjack.model

import blackjack.model.trump.CardNumber
import blackjack.model.trump.Cards

class TrumpRule : Rule {
    override fun getScore(cards: Cards): Score {
        var aceCount = cards.countAce()
        var score = cards.getHighestScore()

        while (!score.isValid() && aceCount > 0) {
            score -= (CardNumber.ACE.scores.highest() - CardNumber.ACE.scores.lowest())
            aceCount--
        }

        return score
    }
}
