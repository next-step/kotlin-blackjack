package blackjack.domain.player

data class Dealer(
    override val name: String
) : Player(name) {
    constructor() : this(NAME)

    override fun isDrawable(): Boolean = getDeckScore() <= DRAWABLE_THRESHOLD

    companion object {
        const val DRAWABLE_THRESHOLD = 16
        const val NAME = "딜러"
    }
}
