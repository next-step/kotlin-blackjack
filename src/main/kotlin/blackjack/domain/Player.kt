package blackjack.domain

class Player(val name: String) {
    var cards: List<Card> = listOf()

    fun addCard(card: Card) {
        cards = cards + card
    }

    fun addCards(cards: List<Card>) {
        this.cards = this.cards + cards
    }

    fun getPoints(): Int {
        var points = 0
        cards.sortedBy { it.denomination.ordinal }.forEach {
            points += when (it.denomination) {
                Denomination.ACE -> getAcePoints(points)
                Denomination.JACK, Denomination.QUEEN, Denomination.KING -> 10
                else -> it.denomination.value.toInt()
            }
        }

        return points
    }

    private fun getAcePoints(points: Int): Int {
        return if (points <= 10) 11
        else 1
    }
}
