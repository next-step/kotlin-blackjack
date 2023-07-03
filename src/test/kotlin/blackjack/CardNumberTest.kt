package blackjack

import blackjack.domain.CardNumber
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardNumberTest {

    @DisplayName("카드의 숫자는 1(Ace)부터 King까지 있다")
    @Test
    fun cardNumber() {
        val actual = CardNumber.values()

        actual.size shouldBe 13
        actual.shouldContainAll(
            listOf(
                CardNumber.ACE, CardNumber.TWO, CardNumber.THREE,
                CardNumber.FOUR, CardNumber.FIVE, CardNumber.SIX,
                CardNumber.SEVEN, CardNumber.EIGHT, CardNumber.NINE, CardNumber.TEN,
                CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING
            )
        )
    }
}
