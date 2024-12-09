package blackjack.domain

open class Player(val name: String) {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()
    val score: Int get() = calculateScore()

    fun addCards(newCards: List<Card>) {
        _cards.addAll(newCards)
    }

    private fun calculateScore(): Int {
        val nonAceSum =
            _cards.filter { it.number != CardNumber.ACE }
                .sumOf { it.number.getPoints() }
        val aceCount = _cards.count { it.number == CardNumber.ACE }

        return if (aceCount > 0) {
            val aceMaxValue = nonAceSum + 11 + (aceCount - 1)
            if (aceMaxValue > 21) nonAceSum + aceCount else aceMaxValue
        } else {
            nonAceSum
        }
    }
}
