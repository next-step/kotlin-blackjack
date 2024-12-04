package blackjack.domain

data class Card(val number: CardNumber, val shape: CardShape) {
    override fun toString(): String {
        return "${number.number}${shape.displayName}"
    }

    companion object {
        private const val INVALID_CARD_FORMAT = "잘못된 카드 형식입니다."
        private val faceCardValues = mapOf("J" to 10, "Q" to 10, "K" to 10, "A" to (1 or 11))

        fun calculateCardValue(cards: List<Card>): Int {
            val nonAceSum =
                cards
                    .filter { extractCardNumber(it) != "A" }
                    .sumOf { calculateSingleCardValue(it) }

            val aceCount = cards.count { extractCardNumber(it) == "A" }

            val aceValue =
                if (aceCount > 0) {
                    val potentialSum = nonAceSum + 11 + (aceCount - 1) * 1
                    if (potentialSum > 21) {
                        aceCount
                    } else {
                        11 + (aceCount - 1)
                    }
                } else {
                    0
                }

            return nonAceSum + aceValue
        }

        private fun calculateSingleCardValue(card: Card): Int {
            val number = extractCardNumber(card)
            return faceCardValues[number] ?: number.toInt()
        }

        private fun extractCardNumber(card: Card): String {
            val matchResult = Regex("^([0-9]+|[A-Z])").find(card.toString())
            return matchResult?.value ?: throw IllegalArgumentException(INVALID_CARD_FORMAT)
        }
    }
}
