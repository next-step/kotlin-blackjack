package blackjack.domain

class Dealer(
    override val cards: Cards
) : Member {

    fun isOverBlackjackNumber(): Boolean = resultScore() > Const.BLACKJACK_NUMBER

    fun isWin(player: Player): Boolean {
        if (isOverBlackjackNumber()) {
            return false
        }

        return this.isNearBlackJackThan(player)
    }
    override fun ableMoreDrawCard() = resultScore() < DRAW_LIMIT_SCORE

    companion object {
        private const val DRAW_LIMIT_SCORE = 17
    }
}
