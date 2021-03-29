package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.card.Score
import blackjack.domain.state.FirstTurn
import blackjack.domain.state.State

class Player(
    name: String,
    initCards: ArrayList<Card> = arrayListOf<Card>()
) : Participant(name, initCards){
    var state: State

    init {
        state = FirstTurn().draw(cards)
    }

    override fun drawCard() {
        if(checkCardDrawAvailable()) {
            val card = CardDeck.drawCard()
            state = state.draw(card)
        }
    }

    override fun checkCardDrawAvailable(): Boolean {
        return !state.isFinished
    }
}