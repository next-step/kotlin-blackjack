package blackjack.domain.card

class Deck(private val cards: MutableList<Card>) {
    init {
        require(cards.size == TOTAL_CARD_SIZE)

        CardPattern.values().forEach {
            curPattern ->
            require(cards.filter { card -> card.pattern == curPattern }.size == CardNumber.values().count())
        }
    }

    // 카드를 전부 뽑는 경우는 고려하지 않음
    fun getNextCard(): Card {
        return cards.removeAt(0)
    }

    companion object {
        val TOTAL_CARD_SIZE = CardNumber.values().count() * CardPattern.values().count()

        fun create(): Deck {
            return Deck(
                CardPattern.values().flatMap { pattern ->
                    CardNumber.values().map { number ->
                        Card(pattern, number)
                    }
                }.toMutableList().apply { shuffle() }
            )
        }
    }
}
