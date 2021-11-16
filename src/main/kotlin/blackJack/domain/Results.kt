package blackJack.domain

class Results(val playerResults: PlayerResults, val dealerResult: DealerResult) {

    companion object {
        fun from(gamePlayers: GamePlayers): Results {
            val playerResults = PlayerResults.of(gamePlayers.getPlayers(), gamePlayers.getDealer())
            val dealerResult = DealerResult.winOrLose(playerResults)
            return Results(playerResults, dealerResult)
        }
    }
}
