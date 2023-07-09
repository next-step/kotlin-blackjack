package blackjack.domain

class Deck private constructor(
    val cards: Cards
) {

    companion object {

        fun create(): Deck {
            return Deck(shuffledCards())
        }

        private fun shuffledCards(): Cards {
            val cards = Denomination.values().flatMap { denomination ->
                CardType.values().map { cardType ->
                    Card(cardType, denomination)
                }
            }
            return Cards.of(cards)
        }
    }
}
