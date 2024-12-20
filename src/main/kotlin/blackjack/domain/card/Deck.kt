package blackjack.domain.card

class Deck {
    private val cards = mutableListOf<PlayingCard>()

    init {
        Suit.values().forEach { suit ->
            Denomination.values().forEach { denomination ->
                cards.add(PlayingCard.of(suit, denomination))
            }
        }
        cards.shuffle()
    }

    fun draw(): PlayingCard {
        if (cards.isEmpty()) {
            throw IllegalStateException("덱에 카드가 없습니다.")
        }
        return cards.removeFirst()
    }
}
