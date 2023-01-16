package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CardVendorTest {
    @Test
    fun `카드 숫자 합이 21 미만일 경우 추가 카드 받기가 가능하다`() {
        assertAll(
            {
                assertThat(
                    CardVendor().isGetExtraCard(
                        listOf(
                            Card(
                                CardNumber.FIVE,
                                CardShape.SPADES
                            )
                        )
                    )
                ).isTrue()
            },
            {
                assertThat(
                    CardVendor().isGetExtraCard(
                        listOf(
                            Card(
                                CardNumber.THREE,
                                CardShape.HEARTS
                            )
                        )
                    )
                ).isTrue()
            }
        )
    }

    @Test
    fun `카드 숫자 합이 21 초과일 경우 추가 카드 받기가 불가능하다`() {
        assertAll(
            {
                assertThat(
                    CardVendor().isGetExtraCard(
                        listOf(
                            Card(CardNumber.TEN, CardShape.SPADES),
                            Card(CardNumber.KING, CardShape.HEARTS),
                            Card(CardNumber.ACE, CardShape.CLUBS)
                        )
                    )
                ).isFalse()
            },
            {
                assertThat(
                    CardVendor().isGetExtraCard(
                        listOf(
                            Card(CardNumber.KING, CardShape.HEARTS),
                            Card(CardNumber.ACE, CardShape.CLUBS)
                        )
                    )
                ).isFalse()
            }
        )
    }

    @Test
    fun `나머지 카드 숫자 합이 0 이상 10이하일 경우 ACE 카드 값을 11로 결정한다`() {
        assertAll(
            {
                assertThat(CardVendor().decideAceCardNumber(0)).isEqualTo(11)
            },
            {
                assertThat(CardVendor().decideAceCardNumber(10)).isEqualTo(11)
            }
        )
    }

    @Test
    fun `나머지 카드 숫자 합이 11이상일 경우 ACE 카드 값을 1로 결정한다`() {
        assertAll(
            {
                assertThat(CardVendor().decideAceCardNumber(11)).isEqualTo(1)
            },
            {
                assertThat(CardVendor().decideAceCardNumber(20)).isEqualTo(1)
            }
        )
    }

    @Test
    fun `카드 값 합계를 계산한다`() {
        assertAll(
            {
                assertThat(
                    CardVendor().sumCardNumbers(
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
                ).isEqualTo(21)
            },
            {
                assertThat(
                    CardVendor().sumCardNumbers(
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
                ).isEqualTo(22)
            },
            {
                assertThat(
                    CardVendor().sumCardNumbers(
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
                ).isEqualTo(33)
            },
            {
                assertThat(
                    CardVendor().sumCardNumbers(
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
                ).isEqualTo(41)
            },
            {
                assertThat(
                    CardVendor().sumCardNumbers(
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
                ).isEqualTo(20)
            }
        )
    }
}
