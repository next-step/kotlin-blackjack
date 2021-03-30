package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust

class Dealer(
    name: String = "딜러",
    initCards: ArrayList<Card> = arrayListOf<Card>()
) : Participant(name, initCards) {

    override fun checkCardDrawAvailable(): Boolean {
        return getScore() <= 16
    }
}