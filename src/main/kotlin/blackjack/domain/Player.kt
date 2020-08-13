package blackjack.domain

import blackjack.view.REPLY_HIT

data class Player(private val name: String) {
    private val cards: Cards = Cards(emptySet())

    fun draw(newCard: Card?): Cards? {
        if (newCard == null) return null
        return Cards(cards.add(newCard))
    }

    fun getChanceToDraw(reply: String): Player? {
        if (reply == REPLY_HIT && !hasScoreMoreThanMax()) {
            draw(Dealer.giveCard()) ?: return null
        }
        return this
    }

    fun hasScoreMoreThanMax(): Boolean = cards.isMoreThanMaxScore(cards)

    fun amountOfCards(): Int = cards.size()

    fun sumOfScores(): Int = cards.sumOfScores()

    fun stateOfCards(): String = cards.toString()

    override fun toString(): String = name
}
