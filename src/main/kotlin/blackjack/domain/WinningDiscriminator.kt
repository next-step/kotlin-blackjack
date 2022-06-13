package blackjack.domain

object WinningDiscriminator {

    fun discrimination(dealer: Player, players: Players): List<WinningResult> {
        return listOf(getDealerResults(dealer, players)) + getPlayerResults(dealer, players)
    }

    private fun getDealerResults(dealer: Player, players: Players): WinningResult {
        return WinningResult(dealer).apply {
            players.forEach { player ->
                val isDealerWin = isDealerWin(dealer, player)
                updateResult(isDealerWin)
            }
        }
    }

    private fun getPlayerResults(dealer: Player, players: Players): List<WinningResult> {
        return players.map { player ->
            WinningResult(player).apply {
                val isPlayerWin = isDealerWin(dealer, player).not()
                updateResult(isPlayerWin)
            }
        }
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
