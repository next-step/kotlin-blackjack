package blackjack

data class Player(
    val name: PlayerName,
    var cards: Cards = Cards(),
) {
    override fun toString(): String {
        return "${name.value}카드: $cards"
    }

    fun isNotBust(): Boolean {
        return cards.isBust().not()
    }

    fun take(newCard: Card) {
        cards.add(newCard)
    }

    fun take(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    fun score(): Int {
        return cards.sum()
    }
}