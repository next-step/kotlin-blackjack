package blackjack.domain

class Player(name: String) : GameParticipants(name) {

    override fun drawCard() {
        cards.drawCard()
    }

    override fun calculateMyCards(): Int {
        return cards.calculateMyCards()
    }
}


