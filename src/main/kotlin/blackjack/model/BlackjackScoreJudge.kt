package blackjack.model

@JvmInline
value class BlackjackScoreJudge(private val limitScore: Int) {

    init {
        require(limitScore > 0) { "limitScore must be greater than 0. but provided limitScore(`$limitScore`)" }
    }

    fun score(deck: CardDeck): Int {
        val score: Int = deck.sumOf { cardNumberScore(it.number) }
        if (score <= limitScore) {
            return score
        }
        return exceptedAceBonusScore(deck, score)
    }

    private fun exceptedAceBonusScore(deck: CardDeck, score: Int): Int {
        val aceCount: Int = deck.count { it.number == TrumpCardNumber.ACE }
        var scoreWithAcePlus: Int = score
        repeat(aceCount) {
            scoreWithAcePlus -= ACE_PLUS_SCORE
            if (scoreWithAcePlus <= limitScore) {
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

    companion object {
        private const val ACE_PLUS_SCORE = 10
    }
}
