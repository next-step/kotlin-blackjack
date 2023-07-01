package blackjack.domain.game

import blackjack.domain.card.Cards
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.PlayerCards
import blackjack.domain.gamer.Players

data class GamerCards(
    val dealerCards: Cards,
    val allPlayerCards: List<PlayerCards>,
) {

    companion object {

        fun create(
            players: Players,
            dealer: Dealer
        ): GamerCards {
            return GamerCards(
                dealerCards = dealer.state.cards,
                allPlayerCards = players.captureAllPlayerCards(),
            )
        }
    }
}
