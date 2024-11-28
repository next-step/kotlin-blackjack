package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackService {
    fun splitInput(input: String): List<String> {
        return input.split(",")
    }
}

class Card {
    val cardList = mutableListOf<String>()
    val cardShape = listOf("하트", "클로버", "스페이드", "다이아")
    val cardNumber = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

    fun createCard() {
        for (shape in cardShape) {
            for (number in cardNumber) {
                cardList.add(number + shape)
            }
        }
    }
}

class BlackJackServiceTest : StringSpec ({
    "사용자의 입력값을 쉼표 기준으로 분리한다." {
        val blackJackService = BlackJackService()
        val input = "kim,da"
        val result = blackJackService.splitInput(input)
        result shouldBe listOf("kim", "da")
    }

    "카드를 생성한다." {
        val card = Card()
        card.createCard()
        card.cardList.size shouldBe 52
    }
})