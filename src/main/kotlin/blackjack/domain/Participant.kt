package blackjack.domain

abstract class Participant(
    val name: String,
    private val cardDeck: CardDeck,
) {
    private val cards = Cards(cardDeck.next(), cardDeck.next())

    val hands
        get() = cards.values

    fun obtain() {
        require(isObtainable()) { "카드를 획득할 수 없습니다." }
        cards.add(cardDeck.next())
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
