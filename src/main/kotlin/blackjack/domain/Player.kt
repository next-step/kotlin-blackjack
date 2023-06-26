package blackjack.domain

class Player(private val name: String, cards: List<Card> = emptyList()) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    fun getName(): String {
        return name
    }

    fun addCard(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun getCards(): List<Card> {
        return _cards.toList()
    }

    fun getScore(): Int {
        return _cards.sumOf { it.cardSymbol.score }
    }
}
