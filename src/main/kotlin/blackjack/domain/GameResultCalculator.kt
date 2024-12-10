package blackjack.domain

object GameResultCalculator {
    fun calculate(
        dealer: Dealer,
        players: Players,
    ): GameResult {
        return if (dealer.isBust()) {
            handleDealerBust(dealer, players)
        } else {
            compareScores(dealer, players)
        }
    }

    private fun handleDealerBust(
        dealer: Dealer,
        players: Players,
    ): GameResult {
        val playerResults =
            players.getPlayers()
                .map { GameResult.PlayerGameResult(it.name, GameResult.Result.WIN) }
        return GameResult(dealer.getTotalValue(), playerResults)
    }

    private fun compareScores(
        dealer: Dealer,
        players: Players,
    ): GameResult {
        val dealerScore = dealer.getTotalValue()
        val results =
            players.getPlayers()
                .map { player ->
                    GameResult.PlayerGameResult(
                        player.name,
                        if (player.getTotalValue() > dealerScore) {
                            GameResult.Result.WIN
                        } else {
                            GameResult.Result.LOSE
                        },
                    )
                }
        return GameResult(dealerScore, results)
    }
}
