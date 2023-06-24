package blackjack.model

object BlackjackScoreJudge {
    const val LIMIT_SCORE = 21

    private const val ACE_PLUS_SCORE = 10

    fun score(deck: HandDeck): Int {
        val score: Int = deck.sumOf { cardNumberScore(it.number) }
        if (score <= LIMIT_SCORE) {
            return score
        }
        return exceptedAceBonusScore(deck, score)
    }

    private fun exceptedAceBonusScore(deck: HandDeck, score: Int): Int {
        val aceCount: Int = deck.aceCount
        var scoreWithAcePlus: Int = score
        repeat(aceCount) {
            scoreWithAcePlus -= ACE_PLUS_SCORE
            if (scoreWithAcePlus <= LIMIT_SCORE) {
                return scoreWithAcePlus
            }
        }
        return scoreWithAcePlus
    }

    private fun cardNumberScore(number: TrumpCardNumber): Int = when (number) {
        TrumpCardNumber.ACE -> 11
        TrumpCardNumber.TWO -> 2
        TrumpCardNumber.THREE -> 3
        TrumpCardNumber.FOUR -> 4
        TrumpCardNumber.FIVE -> 5
        TrumpCardNumber.SIX -> 6
        TrumpCardNumber.SEVEN -> 7
        TrumpCardNumber.EIGHT -> 8
        TrumpCardNumber.NINE -> 9
        TrumpCardNumber.TEN -> 10
        TrumpCardNumber.JACK -> 10
        TrumpCardNumber.QUEEN -> 10
        TrumpCardNumber.KING -> 10
    }
}
