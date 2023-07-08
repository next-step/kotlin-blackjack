package domain.turn

import domain.card.CardDeck
import domain.dealer.Dealer
import domain.player.Players

open class Turn(
    val dealer: Dealer,
    val players: Players,
    private val cardDeck: CardDeck
) {

    fun playersCanTakeNextTurn(): Players {
        return players.playersCanReceiveMoreCard()
    }

    fun proceed(onDealerReceiveCard: (() -> Unit)? = null): Turn {
        if (players.noMoreHit() || dealer.isBlackJack) return FinalTurn(
            dealer,
            players,
            cardDeck
        )
        return Turn(dealer, players, cardDeck).apply { hit(onDealerReceiveCard) }
    }

    private fun hit(onDealerReceiveCard: (() -> Unit)? = null) {
        players.hit(cardDeck)
        if (dealer.canReceiveMoreCard) {
            dealer.hit(cardDeck.pop())
            onDealerReceiveCard?.invoke()
        }
    }
}
