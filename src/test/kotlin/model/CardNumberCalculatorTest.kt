package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CardNumberCalculatorTest {
    @Test
    fun `카드 숫자 합이 21이하 인지 판단한다`() {
        assertAll(
            {
                assertThat(
                    CardNumberCalculator().isUnderTwentyOne(
                        listOf(
                            Card(
                                CardNumber.FIVE,
                                CardShape.SPADES
                            )
                        )
                    )
                ).isSameAs(true)
            },
            {
                assertThat(
                    CardNumberCalculator().isUnderTwentyOne(
                        listOf(
                            Card(CardNumber.TEN, CardShape.SPADES),
                            Card(CardNumber.KING, CardShape.HEARTS),
                            Card(CardNumber.ACE, CardShape.CLUBS)
                        )
                    )
                ).isSameAs(false)
            }
        )
    }
}
