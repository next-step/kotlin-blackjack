package blackjack.domain

class Player(
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

    fun isObtainable(): Boolean {
        return cards.isLessThanBlackjack()
    }
}
