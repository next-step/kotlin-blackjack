package blackjack.domain.participants

import blackjack.domain.card.Card

class Dealer(
    name: String = "딜러",
    initCards: ArrayList<Card> = arrayListOf<Card>()
) : Participant(name, initCards) {

    override fun checkCardDrawAvailable(): Boolean {
        return getScore() <= 16
    }
}
