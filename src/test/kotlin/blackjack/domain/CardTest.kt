package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CardTest {
    @ParameterizedTest
    @MethodSource("provideDataForIsOverMaxSumTest")
    fun `카드의 합이 21을 넘는지 여부를 확인`(
        card: Card,
        currentSum: Int,
        expectedResult: Boolean,
    ) {
        // When
        val result = card.isOverMaxSum(currentSum)

        // Then
        assertThat(result).isEqualTo(expectedResult)
    }

    @ParameterizedTest
    @MethodSource("provideDataForSumValuesTest")
    fun `임의의 카드 리스트가 주어졌을 때 카드의 합이 룰과 일치하는지 확인`(
        cards: Cards,
        expectedSum: Int,
    ) {
        // When
        val sum = cards.sumValues()

        // Then
        assertThat(sum).isEqualTo(expectedSum)
    }

    companion object {
        @JvmStatic
        fun provideDataForIsOverMaxSumTest(): List<Arguments> {
            return listOf(
                Arguments.of(Card(Rank.ACE, Suit.HEARTS), 20, false),
                Arguments.of(Card(Rank.ACE, Suit.HEARTS), 21, true),
            )
        }

        @JvmStatic
        fun provideDataForSumValuesTest(): List<Arguments> {
            return listOf(
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Rank.TWO, Suit.HEARTS),
                            Card(Rank.THREE, Suit.HEARTS),
                            Card(Rank.FOUR, Suit.HEARTS),
                        ),
                    ),
                    // 2 + 3 + 4
                    9,
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Rank.ACE, Suit.HEARTS),
                            Card(Rank.TEN, Suit.HEARTS),
                        ),
                    ),
                    // 11 + 10
                    21,
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Rank.ACE, Suit.HEARTS),
                            Card(Rank.ACE, Suit.HEARTS),
                        ),
                    ),
                    // 11 + 1
                    12,
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Rank.ACE, Suit.HEARTS),
                            Card(Rank.TEN, Suit.HEARTS),
                            Card(Rank.TEN, Suit.HEARTS),
                        ),
                    ),
                    21,
                ),
            )
        }
    }
}
