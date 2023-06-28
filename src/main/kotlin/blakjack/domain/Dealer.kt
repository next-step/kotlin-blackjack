package blakjack.domain

class Dealer {
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
