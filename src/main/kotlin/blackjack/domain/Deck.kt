package blackjack.domain

class Deck private constructor(
    val cards: Cards
) {
    private var index = INIT_INDEX

    fun draw(): Card {
        return cards.get(index++)
    }

    companion object {

        private const val INIT_INDEX = 0

        fun create(): Deck {
            return Deck(shuffledCards())
        }

        private fun shuffledCards(): Cards {
            val cards = Denomination.values().flatMap { denomination ->
                CardType.values().map { cardType ->
                    Card(cardType, denomination)
                }
            }
            return Cards.shuffled(cards)
        }
    }
}
