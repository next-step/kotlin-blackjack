package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CardTest {
    @ParameterizedTest
    @MethodSource("provideAceCards")
    fun `Ace 카드는 1 또는 11로 점수 계산 가능하다`(card: Card) {
        val expectedPossibleScore = PossibleScore(setOf(Score.from(1), Score.from(11)))
        assertEquals(expectedPossibleScore, card.possibleScore)
    }

    @ParameterizedTest
    @MethodSource("provideNumberCards")
    fun `숫자 카드는 카드의 숫자를 점수로 계산한다`(card: Card) {
        val expectedPossibleScore = PossibleScore(setOf(Score.from(card.rank.number)))
        assertEquals(expectedPossibleScore, card.possibleScore)
    }

    @ParameterizedTest
    @MethodSource("provideCourtCards")
    fun `King, Queen, Jack은 10으로 계산한다`(card: Card) {
        val expectedPossibleScore = PossibleScore(setOf(Score.from(10)))
        assertEquals(expectedPossibleScore, card.possibleScore)
    }

    companion object {
        @JvmStatic
        fun provideAceCards() = makeCards(
            Suit.values().toList(),
            listOf(Rank.ACE)
        ).map { Arguments.of(it) }

        @JvmStatic
        fun provideNumberCards() = makeCards(
            Suit.values().toList(),
            listOf(Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN)
        ).map { Arguments.of(it) }

        @JvmStatic
        fun provideCourtCards() = makeCards(
            Suit.values().toList(),
            listOf(Rank.JACK, Rank.QUEEN, Rank.KING)
        ).map { Arguments.of(it) }

        private fun makeCards(suits: List<Suit>, ranks: List<Rank>): List<Card> {
            return suits.flatMap { suit ->
                ranks.map { rank ->
                    Card(suit, rank)
                }
            }
        }
    }
}
