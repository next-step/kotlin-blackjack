package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardTest {

    @DisplayName("카드는 숫자 1종과 무늬 1종을 가진다")
    @Test
    fun numberCard() {
        val card = Card(CardNumber.Seven, Suit.Heart)

        card.cardNumber shouldBe CardNumber.Seven
        card.suit shouldBe Suit.Heart
    }
}
