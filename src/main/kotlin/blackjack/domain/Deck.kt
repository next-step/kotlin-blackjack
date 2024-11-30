package blackjack.domain

class Deck {
    private val cards: MutableList<Card> = Card.ALL_CARDS.toMutableList()

    fun drawCard(): Card {
        if (cards.isEmpty()) {
            throw NoSuchElementException("더 이상 덱에서 뽑을 카드가 없습니다.")
        }
        return cards.removeAt(cards.size - 1)
    }

    fun shuffle() {
        cards.shuffle()
    }
}
