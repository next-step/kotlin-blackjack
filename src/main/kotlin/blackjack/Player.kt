package blackjack

class Player(
    val name: String,
    card1: Card,
    card2: Card,
) {
    private val cards = Cards(card1, card2)

    fun obtain(card: Card) {
        require(isObtainable()) { "카드를 획득할 수 없습니다." }
        cards.add(card)

    }

    fun sumOfCards(): Int {
        return cards.sum()
    }

    fun isObtainable(): Boolean {
        return cards.isLessThanBlackjack()
    }
}
