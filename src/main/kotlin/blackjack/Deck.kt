package blackjack

class Deck {
    private val cards: MutableList<Card> = Card.ALL_CARDS.toMutableList()

    fun drawCard(): Card {
        shuffle()
        if (cards.isEmpty()) {
            throw NoSuchElementException("더 이상 덱에서 뽑을 카드가 없습니다.")
        }
        return cards.removeAt(cards.size - 1)
    }

    private fun shuffle() {
        cards.shuffle()
    }
}
