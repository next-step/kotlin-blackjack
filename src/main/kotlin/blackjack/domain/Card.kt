package blackjack.domain

import kotlin.random.Random

class Card {
    private val cardShape = listOf("하트", "클로버", "스페이드", "다이아")
    private val cardNumber = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    val cardList = mutableListOf<String>()

    init {
        createCard()
    }

    private fun createCard() {
        for (shape in cardShape) {
            for (number in cardNumber) {
                cardList.add("$number$shape")
            }
        }
    }

    fun drawCards(count: Int): List<String> {
        val drawnCards = mutableListOf<String>()

        if (count <= 0) {
            throw IllegalArgumentException(DRAW_CARD_EXCEPTION_MESSAGE)
        }

        repeat(count) {
            val randomIndex = Random.nextInt(cardList.size)
            val card = cardList.removeAt(randomIndex)
            drawnCards.add(card)
        }

        return drawnCards
    }

    companion object {
        private const val DRAW_CARD_EXCEPTION_MESSAGE = "1장 이상의 카드를 뽑아야 합니다."
        private const val INVALID_CARD_FORMAT = "잘못된 카드 형식입니다."
        private val faceCardValues = mapOf("J" to 10, "Q" to 10, "K" to 10, "A" to (1 or 11))

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
