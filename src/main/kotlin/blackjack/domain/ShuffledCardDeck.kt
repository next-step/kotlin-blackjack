package blackjack.domain

class ShuffledCardDeck: CardDeck {
    private var deck = deck()

    override fun next(): Card {
        if (!deck.hasNext()) {
            deck = deck()
        }
        return deck.next()
    }

    private fun deck(): Iterator<Card> {
        return cards()
            .shuffled()
            .iterator()
    }

    private fun cards(): List<Card> {
        return Shape.values().flatMap { shape ->
            Number.values().map { number ->
                Card(number, shape)
            }
        }
    }
}

