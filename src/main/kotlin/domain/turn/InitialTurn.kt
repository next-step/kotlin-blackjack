package domain.turn

import domain.card.CardDeck
import domain.dealer.Dealer
import domain.player.Players

object InitialTurn : Turn {
    override fun proceed(
        dealer: Dealer,
        players: Players,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit
    ) {
        players.hit(cardDeck)
        dealer.hit(cardDeck)
        players.hit(cardDeck)
        dealer.hit(cardDeck)
        changeState(IntermediateTurn)
    }
}
