package blackjack.domain

class Dealer(playerCards: PlayerCards = PlayerCards()) : Player("딜러", playerCards) {

    override fun canDraw(): Boolean = score < CARD_DRAW_THRESHOLD

    companion object {
        private val CARD_DRAW_THRESHOLD = Score(17)
    }
}
