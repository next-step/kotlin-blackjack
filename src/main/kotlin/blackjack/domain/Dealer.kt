package blackjack.domain

class Dealer(
    playerCards: PlayerCards = PlayerCards(),
) : User("딜러", playerCards) {
    override fun openedCards(): List<Card> = playerCards.cards.take(1)

    override fun canDraw(): Boolean = score < CARD_DRAW_THRESHOLD

    fun getPlayerBetResult(player: Player): ResultStatus {
        return when {
            isBust() -> ResultStatus.WinByDealerBust
            player.isBust() -> ResultStatus.LoseByBust
            isBlackJack() && player.isBlackJack() -> ResultStatus.DrawWithBlackJack
            player.isBlackJack() -> ResultStatus.WinByBlackJack
            score > player.score -> ResultStatus.LoseByScore
            score < player.score -> ResultStatus.WinByScore
            else -> ResultStatus.Draw
        }
    }

    companion object {
        private val CARD_DRAW_THRESHOLD = Score(17)
    }
}
