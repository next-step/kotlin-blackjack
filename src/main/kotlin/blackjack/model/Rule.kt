package blackjack.model

object Rule {
    fun getScore(cards: Cards): Score {
        var aceCount = cards.countAce()
        var score = cards.getHighestScore()

        while (score > Score.MAXIMUM && aceCount > 0) {
            score -= (CardNumber.ACE.scores.highest() - CardNumber.ACE.scores.lowest())
            aceCount--
        }

        return score
    }
}
