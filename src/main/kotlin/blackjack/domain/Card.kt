package blackjack.domain

class Card() {
    lateinit var number: CardNumber
        private set
    lateinit var shape: CardShape
        private set

    override fun toString(): String {
        return "$number of $shape"
    }

    companion object {
        private const val INVALID_CARD_FORMAT = "잘못된 카드 형식입니다."
        private val faceCardValues = mapOf("J" to 10, "Q" to 10, "K" to 10, "A" to (1 or 11))

        fun create(number: CardNumber, shape: CardShape): Card {
            val card = Card()
            card.number = number
            card.shape = shape
            return card
        }

        fun calculateCardValue(cards: List<String>): Int {
            val sum = cards.sumOf { calculateSingleCardValue(it) }
            val aceCount = cards.count { extractCardNumber(it) == "A" }

            return adjustAceValue(sum, aceCount)
        }

        private fun calculateSingleCardValue(card: String): Int {
            val number = extractCardNumber(card)
            return if (number == "A") {
                11
            } else {
                faceCardValues[number] ?: number.toInt()
            }
        }

        private fun adjustAceValue(
            sum: Int,
            aceCount: Int,
        ): Int {
            var adjustedSum = sum
            var remainingAces = aceCount

            while (adjustedSum > 21 && remainingAces > 0) {
                adjustedSum -= 10
                remainingAces--
            }

            return adjustedSum
        }

        private fun extractCardNumber(card: String): String {
            val matchResult = Regex("^([0-9]+|[A-Z])").find(card)
            return matchResult?.value ?: throw IllegalArgumentException(INVALID_CARD_FORMAT)
        }
    }
}
