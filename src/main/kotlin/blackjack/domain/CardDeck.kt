package blackjack.domain

class CardDeck(
    cards: Set<Card> = CardPattern.values().flatMap { pattern ->
        CardValue.values().map { Card(pattern = pattern, value = it) }
    }
        .shuffled()
        .toSet()
) {
    private val cards: MutableSet<Card> = cards.toMutableSet()
    fun pick(): Card {
        if (cards.isEmpty()) throw IllegalStateException("there are no card left")

        val card = cards.first()
        cards.remove(card)

        return card
    }
}
