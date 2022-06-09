package blackjack.domain.card

sealed interface Card {
    val cardSuit: CardSuit
    val description: String
    var number: Int

    data class BasicCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append(number).append(cardSuit.description) },
        override var number: Int = 0
    ) : Card

    data class AceCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append("A").append(cardSuit.description) },
        override var number: Int = 11
    ) : Card

    data class JackCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append("J").append(cardSuit.description) },
        override var number: Int = 10
    ) : Card

    data class QueenCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append("Q").append(cardSuit.description) },
        override var number: Int = 10
    ) : Card

    data class KingCard(
        override val cardSuit: CardSuit,
        override val description: String = buildString { append("K").append(cardSuit.description) },
        override var number: Int = 10
    ) : Card

    companion object {
        val BASIC_CARD_RANGE = IntRange(2, 10)
    }
}
