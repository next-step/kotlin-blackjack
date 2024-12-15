package betting

import blackjack.dealer.Dealer
import blackjack.player.Player
import blackjack.player.Players

data class TurnResult(
    val players: Players,
    val dealer: Dealer,
) {
    companion object{
        fun status(
            players: List<Player>,
            dealer: Dealer,
        ): TurnResult = TurnResult(players = Players(players), dealer = dealer)

        fun status(
            players: Players,
            dealer: Dealer,
        ): TurnResult = TurnResult(players = players, dealer = dealer)
    }
}
