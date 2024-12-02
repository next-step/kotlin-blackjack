package blackjack

data class Player(
    val name: PlayerName,
    var cards: Cards,
) {
    override fun toString(): String {
        return "${name.value}카드: $cards"
    }

    fun isNotBust(): Boolean {
        return cards.isBust().not()
    }

    fun take(card: Card) {
        this.cards = cards.addWith(card)
    }

    fun score(): Int {
        return cards.sum()
    }
}