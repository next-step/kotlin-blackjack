package blackjack.domain.card

class Deck {
    private val cards = PlayingCard.createDeck().toMutableList().apply { shuffle() }

    fun draw(): PlayingCard {
        if (cards.isEmpty()) {
            throw IllegalStateException("덱에 카드가 없습니다.")
        }
        return cards.removeFirst()
    }
}
