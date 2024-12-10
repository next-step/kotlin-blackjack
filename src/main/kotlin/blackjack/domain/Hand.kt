package blackjack.domain

class Hand(private val cards: List<Card> = listOf()) {
    fun addCards(newCards: List<Card>): Hand {
        return Hand(cards + newCards)
    }

    fun calculateScore(): Int {
        val nonAceSum = cards
            .filter { it.number != CardNumber.ACE }
            .sumOf { it.number.getPoints() }
        val aceCount = cards.count { it.number == CardNumber.ACE }

        return if (aceCount > 0) {
            val aceMaxValue = nonAceSum + 11 + (aceCount - 1)
            if (aceMaxValue > 21) nonAceSum + aceCount else aceMaxValue
        } else {
            nonAceSum
        }
    }

    fun getCards(): List<Card> = cards.toList()
}