package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

data class BlackJackResult(
    val dealerResult: DealerResult,
    val playerResults: List<PlayerResult>,
) {

    companion object {

        fun from(dealer: Dealer, players: List<Player>): BlackJackResult {
            val playerResults = players.map {
                GamerResultFactory.getPlayerResult(dealer, it)
            }
            val dealerResult = GamerResultFactory.getDealerResult(playerResults)
            return BlackJackResult(dealerResult, playerResults)
        }
    }
}

