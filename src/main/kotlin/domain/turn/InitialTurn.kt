package domain.turn

import domain.card.CardDeck
import domain.card.ShuffledCardDeck
import domain.dealer.Dealer
import domain.player.Players

class InitialTurn(
    private val dealer: Dealer,
    private val players: Players,
    private val cardDeck: CardDeck = ShuffledCardDeck.createNew()
) {
    fun proceed(): Turn {
        dealer.hit(cardDeck.pop())
        dealer.hit(cardDeck.pop())
        players.hit(cardDeck)
        players.hit(cardDeck)
        return Turn(dealer, players, cardDeck)
    }
}
