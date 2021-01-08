package blackjack.domain

data class Player(private val name: String) {

    private val cards: Cards = Cards(mutableSetOf<Card>())

    fun playerScore(): Int {

        return cards.getTotalScore()
    }

    fun draw(card: Card): Player {
        cards.addCard(card)
        return this
    }

    fun stateCards(): String {
        return cards.toString()
    }

    fun amountOfCards(): Int {
        return cards.amountOfCards()
    }

    fun chooseDraw(reply: String, card: Card): Player? {
        if (!cards.isMoreThanMax() && reply == "y") {
            draw(card) ?: return null
        }
        return this
    }

    fun hasMoreThanMax(): Boolean {
        return cards.isMoreThanMax()
    }

    override fun toString(): String {
        return name
    }
}
