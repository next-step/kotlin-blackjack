package blackjack.domain

data class Player(private val name: String) {
    private val cards: Cards = Cards(emptySet<Card>())

    fun draw(deck: Deck) {
        val newCard = deck.giveCard(deck.shuffled())
    }

    val amountOfScores = cards.getTotalScore()

    val amountOfCards = cards.size()

    val stateOfCards = cards.toString()

    fun hasScoreMoreThanMax() = cards.isMoreThanMaxNumber(amountOfScores)

    override fun toString(): String {
        return name.toString()
    }
}


fun main() {
    val player1 = Player("mark")
    println(player1.toString())
}