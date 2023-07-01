package blackjack.domain.game

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player

data class PlayerProfitResult(
    val playerName: String,
    val profit: Double,
) {

    companion object {

        private const val LOSE_MULTIPLE = -1.0
        private const val WIN_MULTIPLE = 1.0
        private const val BLACK_JACK_WIN_MULTIPLE = 1.5
        private const val TIE_MULTIPLE = 0.0

        fun create(
            player: Player,
            dealer: Dealer
        ): PlayerProfitResult {
            val profitMultiple = when {
                player.state.isBust() -> LOSE_MULTIPLE
                dealer.state.isBust() -> if (player.state.isBlackJack()) BLACK_JACK_WIN_MULTIPLE else WIN_MULTIPLE
                player.state.isBlackJack() -> if (dealer.state.isBlackJack()) TIE_MULTIPLE else BLACK_JACK_WIN_MULTIPLE
                dealer.state.isBlackJack() -> LOSE_MULTIPLE
                dealer.state.cards.score < player.state.cards.score -> WIN_MULTIPLE
                dealer.state.cards.score == player.state.cards.score -> TIE_MULTIPLE
                else -> LOSE_MULTIPLE
            }
            return PlayerProfitResult(
                playerName = player.name,
                profit = player.betAmount * profitMultiple
            )
        }
    }
}

fun List<PlayerProfitResult>.totalProfit(): Double {
    return sumOf { it.profit }
}
