package blackjack.domain.result.game

import blackjack.domain.Dealer
import blackjack.domain.player.Players
import blackjack.domain.result.Result

data class GameResult(
    val dealerResults: DealerResult,
    val playersResult: List<PlayerResult>,
) : Result() {

    companion object {
        fun of(players: Players, dealer: Dealer): GameResult {
            val playersResult = players.all.map { PlayerResult.of(it, dealer.player) }
            return GameResult(
                DealerResult.of(dealer, playersResult),
                playersResult
            )
        }
    }
}
