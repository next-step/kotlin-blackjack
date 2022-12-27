package blackjack.domain.card

data class Card(
    val cardNumber: CardNumber,
    val cardShape: CardShape
) {
    val candidateScores: Set<Int> = cardNumber.candidateNumbers

    companion object {
        val ALL_CARD_LIST: List<Card> =
            CardNumber.values().flatMap { cardNumber ->
                CardShape.values().map { cardShape ->
                    Card(cardNumber, cardShape)
                }
            }
    }
}
