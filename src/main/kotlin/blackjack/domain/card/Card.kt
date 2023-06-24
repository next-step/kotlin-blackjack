package blackjack.domain.card

data class Card(
    val shape: CardShape,
    val denomination: CardDenomination,
) {

    companion object {

        val ALL_CARDS = CardShape.ALL_CARD_SHAPES.flatMap { cardShape ->
            createCards(cardShape, CardDenomination.ALL_CARD_DENOMINATIONS)
        }

        private fun createCards(
            cardShape: CardShape,
            cardDenominations: Array<CardDenomination>,
        ): List<Card> {
            return cardDenominations.map { denomination -> Card(cardShape, denomination) }
        }
    }
}
