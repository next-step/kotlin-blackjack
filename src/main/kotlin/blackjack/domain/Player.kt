package blackjack.domain

data class Player(val info: Playerinfo) {
    private val cards: Cards = Cards(emptyList<Card>() as MutableList<Card>)

    fun draw(deck: Deck) {
        val newCard = deck.giveCard(deck.shuffled())
    }

    val score = cards.getTotalScore()

    val amountOfCards = cards.size()

    val stateOfCards = cards.toString()

    fun hasScoreMoreThanMax() = cards.isMoreThanMaxNumber(score)
}
