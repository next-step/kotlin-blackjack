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
    }
}
