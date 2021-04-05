package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CardsTest {

    private val card1 = Card(Suit.HEART, Denomination.ACE)
    private val card2 = Card(Suit.CLUB, Denomination.ACE)
    private val card3 = Card(Suit.DIAMOND, Denomination.ACE)

    @Test
    fun `Cards 는 같은 두장의 카드로 만들 경우 예외처리`() {
        assertThrows<IllegalArgumentException> { Cards(listOf(card1, card1)) }
    }

    @Test
    fun `카드를 추가할 수 있다`() {
        val cards = Cards(listOf(card1, card2))
        val result = cards.add(card3)
        assertThat(result.elements).containsExactly(card1, card2, card3)
    }

    @ParameterizedTest
    @MethodSource("provideHasUnderOrSameScore21")
    fun `각 카드 점수의 합 중에서 블랙잭 점수보다 작거나 같은 점수가 카드들의 점수이다`(cards: Cards, expected: Score) {
        val result = cards.score
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideHasNotUnderOrSameScore21")
    fun `각 카드 점수의 합 중에서 블랙잭보다 작거나 같은 점수가 없는 경우 가장 작은 점수가 카드들의 점수이다`(cards: Cards, expected: Score) {
        val result = cards.score
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun provideHasUnderOrSameScore21(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.TWO),
                            Card(Suit.DIAMOND, Denomination.THREE),
                            Card(Suit.SPADE, Denomination.FOUR)
                        )
                    ),
                    Score(9) // 2 + 3 + 4
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.ACE),
                            Card(Suit.DIAMOND, Denomination.KING),
                            Card(Suit.SPADE, Denomination.FOUR)
                        )
                    ),
                    Score(15) // 1 + 10 + 4
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.ACE),
                            Card(Suit.DIAMOND, Denomination.ACE),
                            Card(Suit.SPADE, Denomination.KING)
                        )
                    ),
                    Score(12) // 1 + 1 + 10
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.ACE),
                            Card(Suit.DIAMOND, Denomination.ACE),
                            Card(Suit.SPADE, Denomination.ACE)
                        )
                    ),
                    Score(13) // 11 + 1 + 1
                )
            )
        }

        @JvmStatic
        private fun provideHasNotUnderOrSameScore21(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.FOUR),
                            Card(Suit.DIAMOND, Denomination.KING),
                            Card(Suit.SPADE, Denomination.JACK)
                        )
                    ),
                    Score(24) // 4 + 10 + 10
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.NINE),
                            Card(Suit.DIAMOND, Denomination.KING),
                            Card(Suit.SPADE, Denomination.JACK)
                        )
                    ),
                    Score(29) // 9 + 10 + 10
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.ACE),
                            Card(Suit.DIAMOND, Denomination.ACE),
                            Card(Suit.SPADE, Denomination.KING),
                            Card(Suit.SPADE, Denomination.JACK)
                        )
                    ),
                    Score(22) // 1 + 1 + 10 + 10
                )
            )
        }
    }
}
