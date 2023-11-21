package blackjack.domain.card

data class Card(val kind: CardKind, val number: CardNumber) {
    companion object {
        const val TOTAL_COUNT = 52

        private val CARDS: List<Card> = CardKind.values().flatMap { kind ->
            CardNumber.values().map { number ->
                Card(kind, number)
            }
        }

        private val CARDS_MAP = CARDS.associateWith { it }

        fun of(cardKind: CardKind, cardNumber: CardNumber): Card {
            return CARDS_MAP[Card(cardKind, cardNumber)] ?: throw IllegalArgumentException()
        }

        fun all(): List<Card> {
            return CARDS
        }
    }
}
