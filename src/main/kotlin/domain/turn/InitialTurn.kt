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
        dealer.hit(cardDeck)
        dealer.hit(cardDeck)
        players.hit(cardDeck)
        players.hit(cardDeck)
        changeState(IntermediateTurn)
    }
}
