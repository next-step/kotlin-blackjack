package blackjack.domain

internal class Player(val name: String) {
    private val _cards = mutableListOf<Card>()

    val cards: List<Card>
        get() = this._cards.toList()

    fun acceptCard(card: Card) {
        this._cards.add(card)
    }

    fun canHit(): Boolean {
        return score() < 21
    }

    fun score(): Int {
        return CardNumber.score(this._cards.map { it.number })
    }
}
