package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust

class Dealer(
    name: String = "딜러",
    initCards: ArrayList<Card> = arrayListOf<Card>()
) : Participant(name, initCards) {
    override fun drawCard() {
        if (checkCardDrawAvailable()) {
            val card = CardDeck.drawCard()
            state = state.draw(card)
        }
    }

    override fun checkCardDrawAvailable(): Boolean {
        return cards.score().value <= 16
    }
}