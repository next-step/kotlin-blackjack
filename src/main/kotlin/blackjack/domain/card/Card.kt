package blackjack.domain.card

data class Card(
    private val cardNumber: CardNumber,
    private val cardShape: CardShape
) {
    val candidateScores: Set<Int> = cardNumber.candidateNumbers

    override fun toString(): String {
        return "$cardNumber$cardShape"
    }

    companion object {
        val ALL_CARD_LIST: List<Card> =
            CardNumber.values().flatMap { cardNumber ->
                CardShape.values().map { cardShape ->
                    Card(cardNumber, cardShape)
                }
            }
    }
}
