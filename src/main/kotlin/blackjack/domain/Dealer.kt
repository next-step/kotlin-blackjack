package blackjack.domain

class Dealer(
    playerCards: PlayerCards = PlayerCards(),
) : User("딜러", playerCards) {
    override fun openedCards(): List<Card> = playerCards.cards.take(CARD_OPEN_COUNT)

    override fun canDraw(): Boolean = playerCards.size <= CARD_TAKE_COUNT_THRESHOLD && score < CARD_DRAW_THRESHOLD

    fun getPlayerBetResult(player: Player): ResultStatus {
        return when {
            player.isBust() -> ResultStatus.LoseByBust
            isBust() -> ResultStatus.WinByDealerBust
            isBlackJack() && player.isBlackJack() -> ResultStatus.DrawWithBlackJack
            player.isBlackJack() -> ResultStatus.WinByBlackJack
            score > player.score -> ResultStatus.LoseByScore
            score < player.score -> ResultStatus.WinByScore
            else -> ResultStatus.Draw
        }
    }

    companion object {
        private val CARD_DRAW_THRESHOLD = Score(17)
        private const val CARD_TAKE_COUNT_THRESHOLD = 2
        private const val CARD_OPEN_COUNT = 1
    }
}
