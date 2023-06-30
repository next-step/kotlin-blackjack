package blakjack.domain

class Dealer(
    name: String = "딜러"
) : Participant(name) {
    val cardDeck = CardDeck.create()

    fun drawOneCard(): Card {
        return cardDeck.getCardRandomly()
    }

    fun drawTwoCards(): Cards {
        return Cards(
            setOf(
                this.drawOneCard(),
                this.drawOneCard(),
            )
        )
    }
}
