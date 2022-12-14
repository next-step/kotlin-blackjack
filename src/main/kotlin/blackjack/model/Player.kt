package blackjack.model

class Player(val name: String) {
    var cards = mutableListOf<Card>()

    fun addCard(card: Card) {
        check(isPickable())
        cards.add(card)
    }

    fun addCards(cards: List<Card>) {
        check(isPickable())
        cards.forEach(::addCard)
    }

    fun isPickable(): Boolean {
        return getScore() < FAIL_THRESHOLD
    }

    private fun getScore(): Int {
        return cards.sumOf { it.getScore() }
    }

    companion object {
        const val FAIL_THRESHOLD = 21
    }
}
