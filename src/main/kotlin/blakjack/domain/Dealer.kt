package blakjack.domain

class Dealer(
    name: String = "딜러"
) : Participant(name) {
    val cardDeck = CardDeck.create()
    val isOver17: Boolean
        get() = this.score >= SEVENTEEN

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

    companion object {
        const val SEVENTEEN = 17
    }
}
