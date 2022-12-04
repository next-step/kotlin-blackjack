package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardTest {

    @DisplayName("주어진 숫자와 무늬에 맞는 카드가 생성된다")
    @Test
    fun createCard() {
        val card = Card(CardNumber.Seven, Suit.Heart)

        "$card" shouldBe "7하트"
    }
}
