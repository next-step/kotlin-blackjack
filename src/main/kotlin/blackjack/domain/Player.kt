package blackjack.domain

class Player(name: String, cards: Cards = Cards(arrayListOf())) : GameParticipants(name, cards) {

    override fun drawCard() {
        cards.drawCard()
    }

    override fun calculateMyCards(): Int {
        return cards.calculateMyCards()
    }
}


