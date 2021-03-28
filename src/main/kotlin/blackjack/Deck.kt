package blackjack

class Deck(cards: List<Card>) {
    private val cards = cards.toMutableList()

    fun draw(): Card {
        return try {
            cards.removeAt(0)
        } catch (e: IndexOutOfBoundsException) {
            throw NoSuchElementException()
        }
    }

    companion object {
        fun newDeck(): Deck {
            return Deck(
                listOf(
                    Card(Suit.SPADES, Symbol.ACE),
                    Card(Suit.SPADES, Symbol.TWO),
                    Card(Suit.SPADES, Symbol.THREE),
                    Card(Suit.SPADES, Symbol.FOUR),
                    Card(Suit.SPADES, Symbol.FIVE),
                    Card(Suit.SPADES, Symbol.SIX),
                    Card(Suit.SPADES, Symbol.SEVEN),
                    Card(Suit.SPADES, Symbol.EIGHT),
                    Card(Suit.SPADES, Symbol.NINE),
                    Card(Suit.SPADES, Symbol.TEN),
                    Card(Suit.SPADES, Symbol.JACK),
                    Card(Suit.SPADES, Symbol.QUEEN),
                    Card(Suit.SPADES, Symbol.KING),
                    Card(Suit.HEARTS, Symbol.ACE),
                    Card(Suit.HEARTS, Symbol.TWO),
                    Card(Suit.HEARTS, Symbol.THREE),
                    Card(Suit.HEARTS, Symbol.FOUR),
                    Card(Suit.HEARTS, Symbol.FIVE),
                    Card(Suit.HEARTS, Symbol.SIX),
                    Card(Suit.HEARTS, Symbol.SEVEN),
                    Card(Suit.HEARTS, Symbol.EIGHT),
                    Card(Suit.HEARTS, Symbol.NINE),
                    Card(Suit.HEARTS, Symbol.TEN),
                    Card(Suit.HEARTS, Symbol.JACK),
                    Card(Suit.HEARTS, Symbol.QUEEN),
                    Card(Suit.HEARTS, Symbol.KING),
                    Card(Suit.DIAMONDS, Symbol.ACE),
                    Card(Suit.DIAMONDS, Symbol.TWO),
                    Card(Suit.DIAMONDS, Symbol.THREE),
                    Card(Suit.DIAMONDS, Symbol.FOUR),
                    Card(Suit.DIAMONDS, Symbol.FIVE),
                    Card(Suit.DIAMONDS, Symbol.SIX),
                    Card(Suit.DIAMONDS, Symbol.SEVEN),
                    Card(Suit.DIAMONDS, Symbol.EIGHT),
                    Card(Suit.DIAMONDS, Symbol.NINE),
                    Card(Suit.DIAMONDS, Symbol.TEN),
                    Card(Suit.DIAMONDS, Symbol.JACK),
                    Card(Suit.DIAMONDS, Symbol.QUEEN),
                    Card(Suit.DIAMONDS, Symbol.KING),
                    Card(Suit.CLUBS, Symbol.ACE),
                    Card(Suit.CLUBS, Symbol.TWO),
                    Card(Suit.CLUBS, Symbol.THREE),
                    Card(Suit.CLUBS, Symbol.FOUR),
                    Card(Suit.CLUBS, Symbol.FIVE),
                    Card(Suit.CLUBS, Symbol.SIX),
                    Card(Suit.CLUBS, Symbol.SEVEN),
                    Card(Suit.CLUBS, Symbol.EIGHT),
                    Card(Suit.CLUBS, Symbol.NINE),
                    Card(Suit.CLUBS, Symbol.TEN),
                    Card(Suit.CLUBS, Symbol.JACK),
                    Card(Suit.CLUBS, Symbol.QUEEN),
                    Card(Suit.CLUBS, Symbol.KING)
                )
            )
        }
    }
}
