package blackjack.domain

object BlackJackDeckGenerator {
    fun getDefaultDeck(): BlackJackDeck {
        val cards = BlackJackCardShape.entries.flatMap(getBlackCardsPerShape()).shuffled()
        return BlackJackDeck(cards)
    }

    private fun getBlackCardsPerShape() =
        { shape: BlackJackCardShape ->
            BlackJackCardNumber.entries.map { number ->
                BlackJackCard.get(
                    shape,
                    number,
                )
            }
        }
}
