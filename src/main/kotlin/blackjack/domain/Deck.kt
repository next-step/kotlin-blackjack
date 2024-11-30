package blackjack.domain

class Deck {
    private val cards: MutableList<Card> = Card.ALL_CARDS.toMutableList()

    fun drawCard(): Card {
        return cards.removeLastOrNull() ?: throw IllegalStateException("더 이상 뽑을 카드가 없습니다.")
    }

    fun shuffle() {
        cards.shuffle()
    }
}
