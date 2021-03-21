package blackjack.domain.player

class Dealer : User(DEALER_NAME) {
    override fun canDraw(): Boolean = calculateScore() <= DEALER_DRAW_CONDITION

    companion object {
        private val DEALER_NAME = UserName("딜러")
        private const val DEALER_DRAW_CONDITION = 16
    }
}
