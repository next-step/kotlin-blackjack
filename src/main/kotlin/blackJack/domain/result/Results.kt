package blackJack.domain.result

import blackJack.domain.player.GamePlayers

class Results private constructor(val playerResults: PlayerResults, val dealerResult: DealerResult) {

    companion object {
        fun from(gamePlayers: GamePlayers): Results {
            val dealerResult = DealerResult()
            val playerResults = PlayerResults.of(gamePlayers.getPlayers(), gamePlayers.getDealer(), dealerResult)
            return Results(playerResults, dealerResult)
        }
    }
}
