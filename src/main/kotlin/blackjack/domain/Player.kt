package blackjack.domain

import blackjack.view.REPLY_HIT

data class Player(private val name: String) {
    private val cards: Cards = Cards(emptySet<Card>())

    fun chooseToDraw(reply: String, deck: Deck): Player? {
        if (REPLY_HIT == reply && !hasScoreMoreThanMax()) {
            draw(deck) ?: return null
        }
        return this
    }

    fun draw(deck: Deck): Cards? {
        val newCard = deck.giveCard(deck.shuffled()) ?: return null
        return Cards(cards.addCard(newCard))
    }

    fun amountOfScores() = cards.getTotalScore()

    fun amountOfCards() = cards.size()

    fun stateOfCards() = cards.toString()

    fun hasScoreMoreThanMax() = cards.isMoreThanMaxNumber(amountOfScores())

    override fun toString(): String {
        return name
    }
}
