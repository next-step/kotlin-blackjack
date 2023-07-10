package blackjack.domain

class Player(
    val name: String = "player"
) {
    var canDraw: Boolean = true
        get() = sum() <= 21
        private set

    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCards(cards: List<Card>): Boolean = _cards.addAll(cards)
    fun addCard(card: Card): Boolean = _cards.add(card)
    fun sum(): Int = cards.sumOf { it.rank.value }
}
