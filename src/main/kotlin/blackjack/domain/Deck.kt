package blackjack.domain

class Deck {

    private val cards: MutableList<Card> = mutableListOf()

    init {
        reset(cards)
        cards.shuffle()
    }

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

        fun reset(cards: MutableList<Card>) {
            cards.clear()
            CardShape.values().forEach { shape ->
                CardCharacter.values().forEach { character ->
                    cards.add(Card(character, shape))
                }
            }
        }
    }
}
