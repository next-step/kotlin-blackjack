package blackjack.domain

class Player(
    private val name: String
) {
    private val cards = mutableSetOf<Card>()

    fun takeCard(card: Card): Boolean {
        return cards.add(card)
    }

    fun calculateCardSum(): Int {
        return cards.sumBy { it.point }
    }
}