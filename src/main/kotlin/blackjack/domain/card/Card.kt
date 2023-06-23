package blackjack.domain.card

data class Card(
    val shape: CardShape,
    val denomination: CardDenomination,
) {

    companion object {

        val ALL_CARDS = CardShape.ALL_CARD_SHAPES
            .map { cardShape -> createCards(cardShape, CardDenomination.ALL_CARD_DENOMINATIONS) }
            .flatten()

        private fun createCards(
            cardShape: CardShape,
            cardDenominations: Array<CardDenomination>,
        ): List<Card> {
            return cardDenominations.map { denomination -> Card(cardShape, denomination) }
        }
    }
}
