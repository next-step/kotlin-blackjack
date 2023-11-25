package blackjack.domain

class Score(private val denominations: List<Denomination>) {
    fun calculate(): Int {
        var score = denominations.sumOf { it.score }
        var numOfAce = denominations.count { it == Denomination.ACE }

        while (numOfAce > 0) {
            if (score > BLACKJACK) {
                score -= (Denomination.ACE.score - Denomination.ACE.aceScore)
            }
            numOfAce -= 1
        }
        return score
    }

    companion object {
        const val BLACKJACK = 21
    }
}
