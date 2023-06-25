package blackjack

import blackjack.domain.Deck
import blackjack.domain.PokerCard

class DeckManager(
    private val deck: Deck = Deck()
) {
    fun drawTwoCards(): Array<PokerCard> {
        return arrayOf(deck.drawCard(), deck.drawCard())
    }

    fun draw(): PokerCard {
        return deck.drawCard()
    }
}
