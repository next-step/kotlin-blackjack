package blackjack.card

import domain.card.BlackjackCard
import domain.card.CardNumber
import domain.card.Suit
import domain.state.StartState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StartStateTest {

    @ParameterizedTest
    @MethodSource("getStartStateTestData")
    fun `플레이어는 카드 두 장을 지급 받으면 시작(Start)이라는 상태가 된다`(card1: BlackjackCard, card2: BlackjackCard) {
        val actual = StartState.start(card1, card2)
        assertThat(actual is StartState).isTrue
    }

    companion object {
        @JvmStatic
        fun getStartStateTestData(): List<Arguments> = listOf(
            Arguments.of(
                BlackjackCard(suit = Suit.HEART, number = CardNumber.ACE),
                BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
            ),
            Arguments.of(
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.JACK),
                BlackjackCard(suit = Suit.HEART, number = CardNumber.JACK),
            ),
            Arguments.of(
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.TEN),
                BlackjackCard(suit = Suit.HEART, number = CardNumber.FIVE),
            ),
        )
    }
}
