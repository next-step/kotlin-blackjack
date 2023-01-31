package model

class Deck(cards: List<Card> = initCards.shuffled()) {
    private val cards: MutableList<Card> = cards.toMutableList()

    fun selectCard(): Card {
        return cards.removeAt(0)
    }
}

val initCards: List<Card> = CardNumber.values().flatMap { num ->
    CardShape.values().map { shape ->
        Card(num, shape)
    }
}
