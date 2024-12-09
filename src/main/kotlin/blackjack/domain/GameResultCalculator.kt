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
        val results = players.getPlayers().associate { it.name to GameResult.Result.WIN }
        return GameResult(dealer.getTotalValue(), results)
    }

    private fun compareScores(
        dealer: Dealer,
        players: Players,
    ): GameResult {
        val dealerScore = dealer.getTotalValue()
        val results =
            players.getPlayers().associate { player ->
                player.name to
                    if (player.getTotalValue() > dealerScore) {
                        GameResult.Result.WIN
                    } else {
                        GameResult.Result.LOSE
                    }
            }
        return GameResult(dealerScore, results)
    }
}
