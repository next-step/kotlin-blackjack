package blackjack.domain

abstract class Participant(
    val name: String, card1: Card, card2: Card,
) {
    private val cards = Cards(card1, card2)

    val hands
        get() = cards.values

    fun obtain(card: Card) {
        require(isObtainable()) { "카드를 획득할 수 없습니다." }
        cards.add(card)
    }

    fun sumOfCards(): Int {
        return cards.sum()
    }

    fun isMoreThanBlackjack(): Boolean {
        return sumOfCards() > BLACKJACK_SCORE
    }

    abstract fun isObtainable(): Boolean
    abstract fun openCards(): List<Card>
}
