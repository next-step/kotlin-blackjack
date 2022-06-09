package blackjack.domain.card

class CardDeck {
    private val cards: MutableList<Card>

    init {
        cards = CardSuit.values()
            .map { makeCard(it) }
            .flatten()
            .toMutableList()
    }

    fun getCurrentCardSize(): Int {
        return cards.size
    }

    fun isContains(card: Card): Boolean {
        return cards.contains(card)
    }

    fun pickCard(): Card {
        val randomIndex = (0 until cards.size).random()
        return cards.removeAt(randomIndex)
    }

    private fun makeCard(cardSuit: CardSuit): List<Card> {
        val cards: MutableList<Card> = mutableListOf()

        for (number in Card.BASIC_CARD_RANGE) {
            cards.add(Card.BasicCard(cardSuit = cardSuit, number = number))
        }

        cards.add(Card.AceCard(cardSuit = cardSuit))
        cards.add(Card.JackCard(cardSuit = cardSuit))
        cards.add(Card.QueenCard(cardSuit = cardSuit))
        cards.add(Card.KingCard(cardSuit = cardSuit))

        return cards
    }
}
