package model

import controller.CardVendor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CardVendorTest {
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
}
