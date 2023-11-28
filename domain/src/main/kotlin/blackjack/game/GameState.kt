package blackjack.game

import blackjack.dealer.Dealer
import blackjack.player.Player

sealed class GameState(
    val players: List<Player>,
    val dealer: Dealer,
) {
    class InitialDeal(
        players: List<Player>,
        dealer: Dealer,
    ) : GameState(players, dealer)

    class PlayerTurn(
        players: List<Player>,
        dealer: Dealer,
        val currentPlayerIndex: Int,
    ) : GameState(players, dealer) {
        val currentPlayer: Player
            get() = players[currentPlayerIndex]
    }

    class DealerTurn(
        players: List<Player>,
        dealer: Dealer,
    ) : GameState(players, dealer)

    class End(
        players: List<Player>,
        dealer: Dealer,
    ) : GameState(players, dealer)
}
