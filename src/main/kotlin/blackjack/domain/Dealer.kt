package blackjack.domain

class Dealer(playerCards: PlayerCards = PlayerCards()) : Player("딜러", playerCards) {

    override fun canDraw(): Boolean = score < CARD_DRAW_THRESHOLD

    fun checkWinners(players: Players) {
        players.forEach { player ->
            val isDealerWin = isWin(player)
            updateWinRecord(isDealerWin)
            player.updateWinRecord(!isDealerWin)
        }
    }

    private fun isWin(player: Player): Boolean {
        return when {
            isBust() -> false
            player.isBust() -> true
            else -> player.score <= score
        }
    }
    companion object {
        private val CARD_DRAW_THRESHOLD = Score(17)
    }
}
