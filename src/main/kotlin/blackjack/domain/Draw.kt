package blackjack.domain

object Draw {
    const val FIRST_DRAW_COUNT = 2
    private const val DRAW_LIMIT = 21

    fun checkDrawable(drawYn: String) = drawYn == "y"
    fun checkDrawable(ownCards: OwnCards) = ownCards.sumCardNumber() < DRAW_LIMIT
}
