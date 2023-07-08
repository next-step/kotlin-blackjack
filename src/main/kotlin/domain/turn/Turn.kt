package domain.turn

import domain.card.CardDeck
import domain.card.Cards
import domain.player.Dealer
import domain.player.Players

open class Turn(
    val dealer: Dealer,
    val players: Players,
    private val cardDeck: CardDeck
) {

    fun playersCanTakeNextTurn(): Players {
        return players.playersCanReceiveMoreCard()
    }

    fun proceed(players: Players, onDealerReceiveCard: (() -> Unit)? = null): Turn {
        if (players.noMorePlayer() || dealer.result() > Cards.BLACKJACK_POINT) return FinalTurn(
            dealer,
            players,
            cardDeck
        )
        return Turn(dealer, players, cardDeck).apply { hit(onDealerReceiveCard) }
    }

    private fun hit(onDealerReceiveCard: (() -> Unit)? = null) {
        players.hit(cardDeck)
        if (dealer.canReceiveMoreCard()) {
            dealer.hit(cardDeck.pop())
            onDealerReceiveCard?.invoke()
        }
    }
}
