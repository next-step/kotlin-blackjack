package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import blackjack.domain.player.MatchingResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class StateMatchingTest {

    @ParameterizedTest
    @MethodSource("provideMyBust")
    fun `자신이 Bust 상태이면 상대의 상태에 관련 없이 패배한다`(mine: State, others: State, expected: MatchingResult) {
        val result = mine.match(others)
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideOthersBust")
    fun `자신이 Bust 상태가 아니고 상대가 Bust 상태인 경우 승리한다`(mine: State, others: State, expected: MatchingResult) {
        val result = mine.match(others)
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideNotBust")
    fun `자신과 상대가 Bust 상태가 아닌 경우 점수가 높은 쪽이 승리한다`(mine: State, others: State, expected: MatchingResult) {
        val result = mine.match(others)
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideNotBustDraw")
    fun `자신과 상대가 Bust 상태가 아니고 점수가 같은 경우 비긴다`(mine: State, others: State, expected: MatchingResult) {
        val result = mine.match(others)
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun provideMyBust(): Stream<Arguments> {
            val dummyCards = Cards(emptyList())
            return Stream.of(
                Arguments.of(Bust(dummyCards), Hit(dummyCards), MatchingResult.LOSE),
                Arguments.of(Bust(dummyCards), Stay(dummyCards), MatchingResult.LOSE),
                Arguments.of(Bust(dummyCards), Blackjack(dummyCards), MatchingResult.LOSE),
                Arguments.of(Bust(dummyCards), Bust(dummyCards), MatchingResult.LOSE)
            )
        }

        @JvmStatic
        fun provideOthersBust(): Stream<Arguments> {
            val dummyCards = Cards(emptyList())
            return Stream.of(
                Arguments.of(Hit(dummyCards), Bust(dummyCards), MatchingResult.WIN),
                Arguments.of(Stay(dummyCards), Bust(dummyCards), MatchingResult.WIN),
                Arguments.of(Blackjack(dummyCards), Bust(dummyCards), MatchingResult.WIN)
            )
        }

        @JvmStatic
        fun provideNotBust(): Stream<Arguments> {
            val lowerScoreCard = Cards(listOf(Card(Suit.HEART, Denomination.TWO)))
            val higherScoreCard = Cards(listOf(Card(Suit.HEART, Denomination.JACK)))
            return Stream.of(
                Arguments.of(Hit(lowerScoreCard), Hit(higherScoreCard), MatchingResult.LOSE),
                Arguments.of(Stay(higherScoreCard), Hit(lowerScoreCard), MatchingResult.WIN),
                Arguments.of(Blackjack(higherScoreCard), Stay(lowerScoreCard), MatchingResult.WIN),
                Arguments.of(Stay(lowerScoreCard), Stay(higherScoreCard), MatchingResult.LOSE)
            )
        }

        @JvmStatic
        fun provideNotBustDraw(): Stream<Arguments> {
            val score6FirstCard = Cards(listOf(Card(Suit.HEART, Denomination.THREE), Card(Suit.CLUB, Denomination.THREE)))
            val score6SecondCard = Cards(listOf(Card(Suit.HEART, Denomination.TWO), Card(Suit.CLUB, Denomination.FOUR)))
            return Stream.of(
                Arguments.of(Hit(score6FirstCard), Hit(score6SecondCard), MatchingResult.DRAW),
                Arguments.of(Stay(score6FirstCard), Hit(score6SecondCard), MatchingResult.DRAW),
                Arguments.of(Blackjack(score6FirstCard), Stay(score6SecondCard), MatchingResult.DRAW),
                Arguments.of(Stay(score6FirstCard), Stay(score6SecondCard), MatchingResult.DRAW)
            )
        }
    }
}
