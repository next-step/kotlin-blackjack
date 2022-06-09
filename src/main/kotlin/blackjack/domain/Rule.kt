package blackjack.domain

sealed interface Rule {
    fun canDraw(score: Score): Boolean
}

object PlayerRule : Rule {
    override fun canDraw(score: Score): Boolean = !score.isBust()
}

object DealerRule : Rule {
    private val CARD_DRAW_THRESHOLD = Score(17)
    override fun canDraw(score: Score): Boolean = score < CARD_DRAW_THRESHOLD
}
