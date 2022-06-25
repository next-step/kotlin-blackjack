package blackjack.domain.participant

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck

class Dealer(
    private val deck: Deck = Deck.release(),
) {
    fun drawCard(): Card = deck.drawCard()
}
