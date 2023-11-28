package blackjack.domain.card

class Deck {

    private val cards: MutableList<Card> = CardShape.values().map { shape ->
        CardCharacter.values().map { character ->
            Card(character, shape)
        }
    }.flatten().shuffled().toMutableList()

    fun draw(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("덱에 남은 카드가 없습니다.")
        }

        return cards.removeLast()
    }

    fun size(): Int {
        return cards.size
    }

    companion object {
        const val MAX_CARD_SIZE = 52
    }
}
