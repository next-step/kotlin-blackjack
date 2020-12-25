package blackjack

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

    fun chooseDraw(card: Card) {
        if (cards.hasMoreThanOver()) {
            throw IllegalArgumentException("Your score already more than max score....")
        } else{
            draw(card)
        }

    }

    override fun toString(): String {
        return name
    }
}