package blackjack.domain

data class Player(private val name: String) {
    private val cards: Cards = Cards(emptySet())

    fun draw(newCard: Card): Set<Card> {
        return cards.add(newCard)
    }

    fun hasScoreMoreThanMax() = cards.isMoreThanMaxScore(cards)

    fun displayCards(): String = cards.toString()

    fun amountOfCards(): Int = cards.size()

    fun sumOfScores(): Int = cards.sumOfScores()

    override fun toString(): String = name
}
