package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HandTest {

    @ParameterizedTest
    @MethodSource("provideBustScore")
    fun `버스트인 경우, 최종 점수는 모든 카드를 더해서 만들 수 있는 가장 작은 수이다`(cards: MutableList<Card>, expectedScoreValue: Int) {
        val hand = cards.let(::Hand)
        val expectedScore = Score.from(expectedScoreValue)
        assertEquals(expectedScore, hand.getBestScore())
    }

    @ParameterizedTest
    @MethodSource("provideBestScore")
    fun `버스트가 아닌 경우, 최종 점수는 모든 카드를 더해서 블랙잭 기준치(21) 이하에서 만들 수 있는 가장 큰 수이다`(
        cards: MutableList<Card>,
        expectedScoreValue: Int
    ) {
        val hand = cards.let(::Hand)
        val expectedScore = Score.from(expectedScoreValue)
        assertEquals(expectedScore, hand.getBestScore())
    }

    companion object {
        @JvmStatic
        fun provideBustScore() = listOf(
            Arguments.of(
                mutableListOf(
                    Card(Suit.HEART, Rank.JACK),
                    Card(Suit.HEART, Rank.QUEEN),
                    Card(Suit.HEART, Rank.ACE),
                    Card(Suit.CLUB, Rank.TWO)
                ),
                23
            ),
            Arguments.of(
                mutableListOf(Card(Suit.HEART, Rank.JACK), Card(Suit.HEART, Rank.QUEEN), Card(Suit.CLUB, Rank.TWO)),
                22
            ),
        )

        @JvmStatic
        fun provideBestScore() = listOf(
            Arguments.of(
                mutableListOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.NINE)),
                20
            ),
            Arguments.of(
                mutableListOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.ACE)),
                12
            ),
            Arguments.of(
                mutableListOf(Card(Suit.CLUB, Rank.ACE), Card(Suit.CLUB, Rank.JACK)),
                21
            ),
        )
    }
}
