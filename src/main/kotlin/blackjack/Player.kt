package blackjack

data class Player(
    val name: String,
    var cards: Cards,
) {
    fun hit(card: Card) {
        cards += card
    }

    fun calculateScore(): Int {
        var scoreSum = cards.sumOf { it.denomination.maxScore() }
        for (i in 1..cards.countAces()) {
            if (scoreSum <= WINNING_NUMBER) {
                break
            }
            scoreSum = scoreSum - Denomination.ACE.maxScore() + Denomination.ACE.score
        }

        return scoreSum
    }

    companion object {
        private const val WINNING_NUMBER = 21
    }
}
