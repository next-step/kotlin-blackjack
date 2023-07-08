package domain.turn

import domain.card.CardDeck
import domain.player.Dealer
import domain.player.Players

data class Turn(
    val dealer: Dealer,
    val players: Players,
    private val cardDeck: CardDeck
) {

    fun playersCanTakeNextTurn(): Players {
        return players.playersCanReceiveMoreCard()
    }

    fun proceed(players: Players, onDealerReceiveCard: (() -> Unit)? = null): Turn {
        return copy(players = players).apply { hit(onDealerReceiveCard) }
    }

    private fun hit(onDealerReceiveCard: (() -> Unit)? = null) {
        players.hit(cardDeck)
        if (dealer.canReceiveMoreCard()) {
            dealer.hit(cardDeck.pop())
            onDealerReceiveCard?.invoke()
        }
    }
}
