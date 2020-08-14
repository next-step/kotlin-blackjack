package blackjack.domain

import blackjack.view.REPLY_HIT

data class Player(private val name: String) {
    private val cards: Cards = Cards(emptySet())

    fun draw(deck: Deck): Cards? {
        val newCard = deck.provideCard(deck.shuffled()) ?: return null
        return Cards(cards.add(newCard))
    }

    fun chooseToDraw(reply: String, deck: Deck): Player? {
        if (reply == REPLY_HIT && !hasScoreMoreThanMax()) {
            draw(deck) ?: return null
        }
        return this
    }

    fun hasScoreMoreThanMax(): Boolean = cards.isMoreThanMaxScore(cards)

    fun amountOfCards(): Int = cards.size()

    fun sumOfScores(): Int = cards.sumOfScores()

    fun stateOfCards(): String = cards.toString()

    override fun toString(): String = name
}
