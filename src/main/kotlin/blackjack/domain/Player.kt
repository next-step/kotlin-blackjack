package blackjack.domain

import blackjack.view.REPLY_RECEIVE

data class Player(private val name: String) {
    private val cards: Cards = Cards(emptySet())

    fun draw(newCard: Card): Set<Card> {
        return cards.add(newCard)
    }

    fun getChanceToDraw(reply: String): Player {
        if (reply == REPLY_RECEIVE && !hasScoreMoreThanMax()) {
            draw(Dealer.giveCard())
        }
        return this
    }

    fun hasScoreMoreThanMax() = cards.isMoreThanMaxScore(cards)

    fun amountOfCards(): Int = cards.size()

    fun sumOfScores(): Int = cards.sumOfScores()

    fun displayCards(): String = cards.toString()

    override fun toString(): String = name
}
