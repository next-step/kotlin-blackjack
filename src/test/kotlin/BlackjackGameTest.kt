import controller.CardNumberCalculator
import controller.CardVendor
import model.Card
import model.CardNumber
import model.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class BlackjackGameTest {
    @Test
    fun `카드 번호가 ACE 인지 확인한다`() {
        assertAll(
            {
                assertThat(CardNumber.ACE.isAce()).isTrue
            },
            {
                assertThat(CardNumber.KING.isAce()).isFalse
            }
        )
    }

    @Test
    fun `카드 번호가 문자열로 변경된다`() {
        assertAll(
            {
                assertThat(CardNumber.ACE.toString()).isEqualTo("A")
            },
            {
                assertThat(CardNumber.KING.toString()).isEqualTo("K")
            },
            {
                assertThat(CardNumber.TWO.toString()).isEqualTo("2")
            }
        )
    }

    @Test
    fun `카드 모양이 문자열로 변경된다`() {
        assertAll(
            {
                assertThat(CardShape.DIAMONDS.toString()).isEqualTo("다이아몬드")
            },
            {
                assertThat(CardShape.CLUBS.toString()).isEqualTo("클로버")
            }
        )
    }

    @Test
    fun `플레이어 카드 숫자 합이 21미만일 경우 추가 카드 받기가 가능하다`() {
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
    fun `플레이어 카드 숫자 합이 21초과일 경우 추가 카드 받기가 불가능하다`() {
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
    fun `딜러 카드 숫자 합이 16이하일 경우 추가 카드 받기가 가능하다`() {
        assertAll(
            {
                assertThat(
                    CardVendor().isGetExtraCardForDealer(
                        listOf(
                            Card(CardNumber.TWO, CardShape.SPADES),
                            Card(CardNumber.KING, CardShape.HEARTS)
                        )
                    )
                ).isTrue
            },
            {
                assertThat(
                    CardVendor().isGetExtraCardForDealer(
                        listOf(
                            Card(CardNumber.THREE, CardShape.HEARTS),
                            Card(CardNumber.ACE, CardShape.CLUBS)
                        )
                    )
                ).isTrue
            }
        )
    }

    @Test
    fun `나머지 카드 숫자 합이 0이상 10이하 일 경우 ACE 카드 값을 11로 결정한다`() {
        assertAll(
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(2)).isEqualTo(11)
            },
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(10)).isEqualTo(11)
            }
        )
    }

    @Test
    fun `나머지 카드 숫자 합이 11이상일 경우 ACE 카드 값을 1로 결정한다`() {
        assertAll(
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(11)).isEqualTo(1)
            },
            {
                assertThat(CardNumberCalculator().decideAceCardNumber(20)).isEqualTo(1)
            }
        )
    }

    @Test
    fun `카드 값 합계를 계산한다`() {
        assertAll(
            {
                assertThat(
                    CardNumberCalculator().sumCardNumbers(
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
                    CardNumberCalculator().sumCardNumbers(
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
                    CardNumberCalculator().sumCardNumbers(
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
                    CardNumberCalculator().sumCardNumbers(
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
                    CardNumberCalculator().sumCardNumbers(
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
