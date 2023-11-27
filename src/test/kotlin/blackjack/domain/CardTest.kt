package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CardTest {
    @ParameterizedTest
    @MethodSource("provideAceCards")
    fun `Ace 카드는 1 또는 11로 점수 계산 가능하다`(sameRankCards: List<Card>) {
        val expectedPossibleScore = PossibleScore(setOf(1, 11))
        sameRankCards.forEach { card ->
            assertEquals(expectedPossibleScore, card.possibleScore)
        }
    }

    @ParameterizedTest
    @MethodSource("provideNumberCards")
    fun `숫자 카드는 카드의 숫자를 점수로 계산한다`(sameRankCards: List<Card>) {
        val expectedPossibleScore = PossibleScore(setOf(sameRankCards[0].rank.number))
        sameRankCards.forEach { card ->
            assertEquals(expectedPossibleScore, card.possibleScore)
        }
    }

    @ParameterizedTest
    @MethodSource("provideCourtCards")
    fun `King, Queen, Jack은 10으로 계산한다`(sameRankCards: List<Card>) {
        val expectedPossibleScore = PossibleScore(setOf(10))
        sameRankCards.forEach { card ->
            assertEquals(expectedPossibleScore, card.possibleScore)
        }
    }

    companion object {
        @JvmStatic
        fun provideAceCards() =
            listOf(Rank.ACE)
                .map { Card.getDeck().filter { card -> card.rank == it } }
                .map { Arguments.of(it) }

        @JvmStatic
        fun provideNumberCards() =
            listOf(Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN)
                .map { Card.getDeck().filter { card -> card.rank == it } }
                .map { Arguments.of(it) }

        @JvmStatic
        fun provideCourtCards() =
            listOf(Rank.JACK, Rank.QUEEN, Rank.KING)
                .map { Card.getDeck().filter { card -> card.rank == it } }
                .map { Arguments.of(it) }
    }
}
