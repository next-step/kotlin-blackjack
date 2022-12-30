package blackjack.domain

class Dealer : Player("딜러") {
    override fun canDraw(): Boolean {
        return score().value <= DEALERS_HIT_RULE
    }

    override fun open(): List<Card> {
        return listOf(
            hand.cards()
                .first()
        )
    }

    fun allOpen(): List<Card> {
        return hand.cards()
    }

    fun result(scores: List<Score>): List<Result> {
        return scores.map {
            when {
                score().isBurst() -> Result.LOSE
                it.isBurst() -> Result.WIN
                score().value > it.value -> Result.WIN
                else -> Result.LOSE
            }
        }
    }

    companion object {
        const val DEALERS_HIT_RULE = 16
    }
}
