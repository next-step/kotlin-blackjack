package blackjack

data class Player(private val name: String) {

    private val cards: Cards = Cards(mutableSetOf<Card>())

    fun playerScore(): Int {

        return cards.getTotalScore()
    }

    fun draw(card: Card){
        return cards.addCard(card)
    }

    fun stateCards(): String {
        return cards.toString()
    }

    fun chooseDraw(card: Card): Player? {
        if (cards.checkOver(card)) {
            draw(card) ?: return null
        }
        return this
    }

    override fun toString(): String {
        return name
    }


}