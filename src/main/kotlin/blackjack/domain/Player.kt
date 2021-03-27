package blackjack.domain

import blackjack.domain.Cards.Companion.WINNING_NUMBER

class Player(name: String, cards: Cards = Cards(arrayListOf())) : GameParticipants(name, cards) {

    override fun drawCard() {
        cards.drawCard()
    }

    override fun calculateMyCards(): Int {
        return cards.calculateMyCards()
    }

    fun checkMyCardsIsOver21(): Boolean {
        return cards.calculateMyCards() > WINNING_NUMBER
    }
}
