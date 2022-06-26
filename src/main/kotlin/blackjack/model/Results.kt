package blackjack.model

class Results(players: Players, dealer: Player) {
    val playerResults: List<PlayerResult>
    val dealerResult: DealerResult

    init {
        players.values
        playerResults = players.values.map { player ->
            PlayerResult(player, isPlayerWin(player, dealer))
        }

        val playerWinCnt = playerResults.count { it.win }
        val dealerWinCnt = playerResults.count { !it.win }
        dealerResult = DealerResult(dealer, dealerWinCnt, playerWinCnt)
    }

    private fun isPlayerWin(player: Player, dealer: Player): Boolean {
        return player.cards.optimalScore().isWinThan(dealer.cards.optimalScore())
    }
}
