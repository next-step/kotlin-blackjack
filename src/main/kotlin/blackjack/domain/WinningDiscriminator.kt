package blackjack.domain

object WinningDiscriminator {
    fun discrimination(dealer: Player, players: Players): List<WinningResult> {
        val dealerResult = WinningResult(dealer)
        val playerResults = players.map { player ->
            WinningResult(player).apply {
                val isDealerWin = isDealerWin(dealer, player)
                updateResult(!isDealerWin)
                dealerResult.updateResult(isDealerWin)
            }
        }
        return listOf(dealerResult) + playerResults
    }

    private fun isDealerWin(dealer: Player, player: Player): ResultStatus {
        return when {
            player.isBust() -> ResultStatus.Win
            dealer.isBust() -> ResultStatus.Lose
            dealer.score > player.score -> ResultStatus.Win
            dealer.score == player.score -> ResultStatus.Draw
            dealer.score < player.score -> ResultStatus.Lose
            else -> ResultStatus.Draw
        }
    }
}
