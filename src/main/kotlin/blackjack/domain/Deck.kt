package blackjack.domain

class Deck(val cards: Cards) {

    fun getCard(): Card {
        return cards.removeAt(0)
    }

    fun getInitGameCards(): Cards {
        val cards = Cards(mutableListOf())
        repeat(2) {
            cards.add(getCard())
        }

        return cards
    }

    companion object {
        fun newDeck(): Deck {
            val cards = mutableListOf<Card>()
            for (i in 0 until Rank.values().size * Suit.values().size) {
                cards.add(Card.valueOf(i))
            }

            cards.shuffle()
            return Deck(Cards(cards))
        }
    }
}
