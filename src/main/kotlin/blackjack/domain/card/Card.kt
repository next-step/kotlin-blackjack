package blackjack.domain.card

sealed interface Card {
    val cardSuit: CardSuit
    val cardType: CardType
    val number: Int

    data class BasicCard(
        override val cardSuit: CardSuit,
        override val cardType: CardType = CardType.BASIC,
        override val number: Int
    ) : Card

    data class AceCard(
        override val cardSuit: CardSuit,
        override val cardType: CardType = CardType.ACE,
        override val number: Int = 11
    ) : Card

    data class JackCard(
        override val cardSuit: CardSuit,
        override val cardType: CardType = CardType.JACK,
        override val number: Int = 10
    ) : Card

    data class QueenCard(
        override val cardSuit: CardSuit,
        override val cardType: CardType = CardType.QUEEN,
        override val number: Int = 10
    ) : Card

    data class KingCard(
        override val cardSuit: CardSuit,
        override val cardType: CardType = CardType.KING,
        override val number: Int = 10
    ) : Card

    companion object {
        val BASIC_CARD_RANGE = IntRange(2, 10)
    }
}
