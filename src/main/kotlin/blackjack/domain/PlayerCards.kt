package blackjack.domain

internal class PlayerCards {
    private val _cards = mutableListOf<Card>()

    val cards: List<Card>
        get() = this._cards.toList()

    fun add(card: Card) {
        this._cards.add(card)
    }

    fun score(): Int {
        return CardNumber.score(this._cards.map { it.number })
    }
}
