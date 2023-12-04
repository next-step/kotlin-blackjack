package blackjack.domain

class Score(private val denominations: List<Denomination>) {

    fun calculate(): Int {
        var score = denominations.sumOf { it.score }
        var numOfAce = denominations.count { it == Denomination.ACE }

        if (denominations.size == 2 && numOfAce == 1 && denominations.containsScoreTen()) {
            return BLACKJACK
        }

        while (numOfAce > 0 && score > BLACKJACK) {
            score -= Denomination.ACE.let { it.score - it.optionScore }
            numOfAce -= 1
        }
        return score
    }

    private fun List<Denomination>.containsScoreTen(): Boolean {
        return any { it.score == 10 }
    }

    companion object {
        const val BLACKJACK = 21
    }
}
