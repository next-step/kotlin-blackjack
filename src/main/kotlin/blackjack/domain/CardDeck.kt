package blackjack.domain

class CardDeck {
    val cards: Cards = getShuffledCards()

    fun hit(): Card {
        return cards.cards.removeFirst()
    }

    private fun getShuffledCards(): Cards {
        val cards = CardSuit.values().flatMap { cardShape ->
            Denomination.values().filterNot { it == Denomination.ACE_10 }.map { cardNumber ->
                Card(cardShape, cardNumber)
            }
        }.toMutableList()

        return Cards(cards.apply { this.shuffle() })
    }
}
