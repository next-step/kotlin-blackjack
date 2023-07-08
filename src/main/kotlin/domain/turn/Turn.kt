package domain.turn

import domain.card.CardDeck
import domain.dealer.Dealer
import domain.player.Players

interface Turn {
    fun proceed(
        dealer: Dealer,
        players: Players,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit
    )
}
