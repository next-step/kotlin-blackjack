package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("플레이어 카드")
class PlayerCardsTest {

    @Test
    fun `카드를 추가한다`() {
        // given
        val playerCards = PlayerCards()

        // when
        playerCards.add(Card(Suit.SPADE, Rank.ACE))

        // then
        playerCards.size shouldBe 1
        playerCards[0] shouldBe Card(Suit.SPADE, Rank.ACE)
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    fun `카드의 합을 구한다`(cards: List<Card>, expected: Int) {
        // given
        val playerCards = PlayerCards()
        cards.forEach { playerCards.add(it) }

        // when
        val actual = playerCards.sum()

        // then
        actual shouldBe expected
    }

    companion object {
        @JvmStatic
        fun provideCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Rank.ACE), Card(Suit.SPADE, Rank.EIGHT)
                    ),
                    19
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Rank.ACE),
                        Card(Suit.SPADE, Rank.ACE),
                        Card(Suit.SPADE, Rank.EIGHT),
                        Card(Suit.SPADE, Rank.ACE)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Rank.ACE),
                        Card(Suit.SPADE, Rank.EIGHT),
                        Card(Suit.SPADE, Rank.TWO)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Rank.ACE),
                        Card(Suit.SPADE, Rank.EIGHT),
                        Card(Suit.SPADE, Rank.TWO),
                        Card(Suit.SPADE, Rank.TEN)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Rank.ACE),
                        Card(Suit.SPADE, Rank.EIGHT),
                        Card(Suit.SPADE, Rank.THREE)
                    ),
                    12
                ),
                Arguments.of(
                    listOf(
                        Card(Suit.SPADE, Rank.ACE),
                        Card(Suit.SPADE, Rank.NINE),
                        Card(Suit.SPADE, Rank.TEN)
                    ),
                    20
                ),
            )
        }
    }
}
