package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class HandsTest {
    @Test
    fun `sum() 카드들의 합계를 더한다`() {
        assertThat((Hands() + Card.CLUB_ACE + Card.CLUB_10).sum).isEqualTo(BLACK_JACK)
    }

    @ParameterizedTest
    @MethodSource("provideHands")
    fun `sum() Ace가 충분히 있으면 무조건 BlackJack과 같거나 낮은 숫자가 나온다`(cards: List<Card>, expectedSum: Int) {
        assertThat(Hands(cards).sum).isEqualTo(expectedSum)
    }

    @Test
    fun `sum() Ace가 모자라면 무조건 BlackJack보다 큰 숫자가 나온다`() {
        assertThat(
            Hands(
                listOf(
                    Card.CLUB_ACE,
                    Card.CLUB_ACE,
                    Card.CLUB_10,
                    Card.CLUB_10,
                    Card.CLUB_10
                )
            ).sum
        ).isEqualTo(32)
    }

    companion object {
        @JvmStatic
        private fun provideHands(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf(Card.CLUB_ACE, Card.CLUB_10), BLACK_JACK),
                Arguments.of(listOf(Card.CLUB_ACE, Card.CLUB_ACE, Card.CLUB_10), 12),
                Arguments.of(listOf(Card.CLUB_ACE, Card.CLUB_ACE, Card.CLUB_ACE, Card.CLUB_10), 13),
                Arguments.of(listOf(Card.CLUB_ACE, Card.CLUB_ACE, Card.CLUB_ACE, Card.CLUB_9), 12)
            )
        }
    }
}
