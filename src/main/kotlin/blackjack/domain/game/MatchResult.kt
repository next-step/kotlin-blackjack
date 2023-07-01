package blackjack.domain.game

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Players

data class MatchResult(
    val gamerCards: GamerCards,
    val gamerProfitResults: GamerProfitResults,
) {

    init {
        require(gamerCards.allPlayerCards.size == gamerProfitResults.playerProfitResults.size) {
            "all player cards size should be match result size"
        }
    }

    companion object {

        fun create(
            players: Players,
            dealer: Dealer
        ): MatchResult {
            return MatchResult(
                gamerCards = GamerCards.create(
                    players = players,
                    dealer = dealer,
                ),
                gamerProfitResults = GamerProfitResults.create(
                    players = players,
                    dealer = dealer,
                )
            )
        }
    }
}
