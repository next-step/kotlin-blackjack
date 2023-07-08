package domain.turn

import domain.card.CardDeck
import domain.dealer.Dealer
import domain.player.Players

object IntermediateTurn : Turn {

    override fun proceed(
        dealer: Dealer,
        players: Players,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit
    ) {
        if (players.noMoreHit() || dealer.isBlackJack || dealer.isBust) {
            changeState(FinalTurn)
            return
        }
        hit(dealer, players, cardDeck)
        changeState(IntermediateTurn)
    }

    private fun hit(
        dealer: Dealer,
        players: Players,
        cardDeck: CardDeck,
    ) {
        players.hit(cardDeck)
        if (dealer.canReceiveMoreCard) {
            dealer.hit(cardDeck)
        }
    }
}
