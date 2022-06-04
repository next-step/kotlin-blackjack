package blackjack.domain

class Player(val name: String, cards: List<Card>) {
    private val cards: MutableList<Card> = cards.toMutableList()

    override fun toString(): String {
        return "$name cards : $cards"
    }
}
