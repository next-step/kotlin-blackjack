package blackjack.domain

class Dealer(
    playerCards: PlayerCards = PlayerCards(),
) : User("딜러", playerCards) {
    override fun openedCards(): List<Card> = playerCards.cards.take(1)

    override fun canDraw(): Boolean = score < CARD_DRAW_THRESHOLD

    fun getBetResult(player: Player): ResultStatus {
        if (isBust()) return ResultStatus.WinByDealerBust
        if (player.isBust()) return ResultStatus.LoseByBust
        if (isBlackJack() && player.isBlackJack()) return ResultStatus.DrawWithBlackJack
        if (player.isBlackJack()) return ResultStatus.WinByBlackJack
        if (score > player.score) return ResultStatus.LoseByScore
        if (score < player.score) return ResultStatus.WinByScore
        return ResultStatus.Draw
    }
    
    companion object {
        private val CARD_DRAW_THRESHOLD = Score(17)
    }
}
