package blackjack

class Player(val name: String, val hand: List<Card>) {
    fun score(): Int {
        val score = hand.sumOf { it.denomination.score }

        if (!hasAce()) {
            return score
        }

        return caculateScoreWithAce(score)
    }

    private fun caculateScoreWithAce(score: Int): Int {
        if (score <= 11) {
            return score + 10
        }

        return score
    }

    private fun hasAce(): Boolean {
        return hand.any { it.denomination == Denomination.ACE }
    }
}
