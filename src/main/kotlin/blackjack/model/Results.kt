package blackjack.model

class Results(players: Players, dealer: Player) {
    val playerResults: List<PlayerResult>
    val dealerResult: DealerResult

    init {
        playerResults = players.values.map { player ->
            PlayerResult(player, player.isWinThen(dealer))
        }

        val playerWinCnt = playerResults.count { it.win }
        val dealerWinCnt = playerResults.count { !it.win }
        dealerResult = DealerResult(dealer, dealerWinCnt, playerWinCnt)
    }
}
