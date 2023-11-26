package blackjack.domain

class Score(private val denominations: List<Denomination>) {
    fun calculate(): Int {
        var score = denominations.sumOf { it.score }
        var numOfAce = denominations.count { it == Denomination.ACE }

        while (numOfAce > 0 && score > TARGET_SCORE) {
            score -= Denomination.ACE.let {  it.score - it.optionScore }
            numOfAce -= 1
        }
        return score
    }

    companion object {
        const val TARGET_SCORE = 21
    }
}
