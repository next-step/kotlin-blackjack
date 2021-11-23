package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

data class BlackJackResult(val gamerRestuls: List<GamerResult>) {

    companion object {

        fun from(dealer: Dealer, players: List<Player>): BlackJackResult {
            val playerResults = players.map {
                PlayerResultFactory.getPlayerResult(dealer, it)
            }
            val dealerResult = GamerResult.getDealerResultFromPlayers(dealer, playerResults)
            return BlackJackResult(listOf(dealerResult) + playerResults)
        }
    }
}

