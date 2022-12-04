package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardTest {

    @DisplayName("숫자 카드가 올바르게 생성된다")
    @Test
    fun numberCard() {
        val card = Card(CardNumber.Seven, Suit.Heart)

        "$card" shouldBe "7하트"
    }

    @DisplayName("K, J, Q 메이저 카드가 올바르게 생성된다")
    @Test
    fun majorCard() {
        val card = Card(CardNumber.Queen, Suit.Spade)

        "$card" shouldBe "Q스페이드"
    }

    @DisplayName("Ace 카드가 올바르게 생성된다")
    @Test
    fun aceCard() {
        val card = Card(CardNumber.Ace, Suit.Diamond)

        "$card" shouldBe "A다이아몬드"
    }
}
