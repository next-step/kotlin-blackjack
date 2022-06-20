package blackjack.domain

class Dealer(
    playerCards: PlayerCards = PlayerCards(),
) : User("딜러", playerCards) {
    override fun openedCards(): List<Card> = currentCards().take(CARD_OPEN_COUNT)

    override fun canDraw(): Boolean = currentCards().size <= CARD_TAKE_COUNT_THRESHOLD && score < CARD_DRAW_THRESHOLD

    fun getPlayerBetResult(player: Player): BetResultStatus {
        return when {
            player.isBust() -> BetResultStatus.LoseByBust
            isBust() -> BetResultStatus.WinByDealerBust
            isBlackJack() && player.isBlackJack() -> BetResultStatus.DrawWithBlackJack
            player.isBlackJack() -> BetResultStatus.WinByBlackJack
            score > player.score -> BetResultStatus.LoseByScore
            score < player.score -> BetResultStatus.WinByScore
            else -> BetResultStatus.Draw
        }
    }

    companion object {
        private val CARD_DRAW_THRESHOLD = Score(17)
        private const val CARD_TAKE_COUNT_THRESHOLD = 2
        private const val CARD_OPEN_COUNT = 1
    }
}
