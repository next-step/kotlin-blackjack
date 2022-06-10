package blackjack.domain.card

sealed interface Card {
    val cardSuit: CardSuit
    val description: String
    val number: Int

    data class BasicCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append(number).append(cardSuit.description) },
        override val number: Int = 0
    ) : Card

    data class AceCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append("A").append(cardSuit.description) }
    ) : Card {
        override val number: Int
            get() = 11
    }

    data class JackCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append("J").append(cardSuit.description) }
    ) : Card {
        override val number: Int
            get() = 10
    }

    data class QueenCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append("Q").append(cardSuit.description) }
    ) : Card {
        override val number: Int
            get() = 10
    }

    data class KingCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append("K").append(cardSuit.description) }
    ) : Card {
        override val number: Int
            get() = 10
    }

    companion object {
        val BASIC_CARD_RANGE = IntRange(2, 10)
    }
}
