package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CardNumberCalculatorTest {
    @Test
    fun `추가 카드 받기 가능 여부를 판단한다`() {
        assertAll(
            {
                assertThat(
                    CardNumberCalculator().isGetExtraCard(
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
                    CardNumberCalculator().isGetExtraCard(
                        listOf(
                            Card(CardNumber.TEN, CardShape.SPADES),
                            Card(CardNumber.KING, CardShape.HEARTS),
                            Card(CardNumber.ACE, CardShape.CLUBS)
                        )
                    )
                ).isSameAs(false)
            },
            {
                assertThat(
                    CardNumberCalculator().isGetExtraCard(
                        listOf(
                            Card(CardNumber.KING, CardShape.HEARTS),
                            Card(CardNumber.ACE, CardShape.CLUBS)
                        )
                    )
                ).isSameAs(false)
            }
        )
    }

    @Test
    fun `ACE 카드 값을 11로 결정한다`() {
        assertAll(
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(2)).isSameAs(11)
            },
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(10)).isSameAs(11)
            }
        )
    }

    @Test
    fun `ACE 카드 값을 1로 결정한다`() {
        assertAll(
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(11)).isSameAs(1)
            },
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(20)).isSameAs(1)
            }
        )
    }

    @Test
    fun `카드 값 합계를 계산한다`() {
        assertAll(
            {
                assertThat(
                    CardNumberCalculator().totalNumber(
                        listOf(
                            Card(
                                CardNumber.TWO,
                                CardShape.SPADES
                            ),
                            Card(
                                CardNumber.NINE,
                                CardShape.HEARTS
                            ),
                            Card(
                                CardNumber.TEN,
                                CardShape.DIAMONDS
                            )
                        )
                    )
                ).isSameAs(21)
            },
            {
                assertThat(
                    CardNumberCalculator().totalNumber(
                        listOf(
                            Card(
                                CardNumber.TWO,
                                CardShape.DIAMONDS
                            ),
                            Card(
                                CardNumber.NINE,
                                CardShape.CLUBS
                            ),
                            Card(
                                CardNumber.TEN,
                                CardShape.SPADES
                            ),
                            Card(
                                CardNumber.ACE,
                                CardShape.HEARTS
                            )
                        )
                    )
                ).isSameAs(22)
            },
            {
                assertThat(
                    CardNumberCalculator().totalNumber(
                        listOf(
                            Card(
                                CardNumber.TWO,
                                CardShape.DIAMONDS
                            ),
                            Card(
                                CardNumber.ACE,
                                CardShape.CLUBS
                            ),
                            Card(
                                CardNumber.TEN,
                                CardShape.SPADES
                            ),
                            Card(
                                CardNumber.KING,
                                CardShape.HEARTS
                            )
                        )
                    )
                ).isSameAs(23)
            },
            {
                assertThat(
                    CardNumberCalculator().totalNumber(
                        listOf(
                            Card(
                                CardNumber.QUEEN,
                                CardShape.DIAMONDS
                            ),
                            Card(
                                CardNumber.ACE,
                                CardShape.CLUBS
                            ),
                            Card(
                                CardNumber.TEN,
                                CardShape.SPADES
                            ),
                            Card(
                                CardNumber.KING,
                                CardShape.HEARTS
                            )
                        )
                    )
                ).isSameAs(31)
            },
            {
                assertThat(
                    CardNumberCalculator().totalNumber(
                        listOf(
                            Card(
                                CardNumber.TWO,
                                CardShape.DIAMONDS
                            ),
                            Card(
                                CardNumber.THREE,
                                CardShape.CLUBS
                            ),
                            Card(
                                CardNumber.ACE,
                                CardShape.SPADES
                            ),
                            Card(
                                CardNumber.FOUR,
                                CardShape.HEARTS
                            )
                        )
                    )
                ).isSameAs(20)
            }
        )
    }
}
