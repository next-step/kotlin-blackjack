package blackjack.domain.card

abstract class AbstractCards(
    val cards: MutableList<Card>
): List<Card> by cards
