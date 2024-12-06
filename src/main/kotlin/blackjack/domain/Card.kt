package blackjack.domain

data class Card(val number: CardNumber, val shape: CardShape) {
    override fun toString(): String {
        return "${number.value}${shape.displayName}"
    }

    companion object {
        private const val INVALID_CARD_FORMAT = "잘못된 카드 형식입니다."
        private val faceCardValues = mapOf("J" to 10, "Q" to 10, "K" to 10, "A" to (1 or 11))

        fun calculateCardValue(cards: List<Card>): Int {
            val nonAceSum = cards
                .filter { it.number != CardNumber.ACE }
                .sumOf { calculateSingleCardValue(it) }

            val aceCount = cards.count { it.number == CardNumber.ACE }

            var totalScore = nonAceSum
            repeat(aceCount) {
                if (totalScore + 11 <= 21) {
                    totalScore += 11
                } else {
                    totalScore += 1
                }
            }

            return totalScore
        }

        private fun calculateSingleCardValue(card: Card): Int {
            return faceCardValues[card.number.name] ?: card.number.value
        }
    }
}
