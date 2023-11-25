package blackjack.domain

class Player(
    val name: String,
    private val deck: Iterator<Card>,
) {
    private val cards = Cards(deck.next(), deck.next())
    val hands
        get() = cards.values

    fun obtain() {
        require(isObtainable()) { "카드를 획득할 수 없습니다." }
        cards.add(deck.next())
    }

    fun sumOfCards(): Int {
        return cards.sum()
    }

    fun isObtainable(): Boolean {
        return cards.isLessThanBlackjack()
    }
}
