package blackjack.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardTest {

    @ParameterizedTest
    @MethodSource("cardCandidateList")
    fun `카드 테스트`(card: Card, name: String) {
        assertThat(card.cardName()).isEqualTo(name)
    }

    companion object {
        @JvmStatic
        fun cardCandidateList(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Card(Suit.SPADE, Number.ACE), "A스페이드"),
                Arguments.of(Card(Suit.HEART, Number.TWO), "2하트"),
                Arguments.of(Card(Suit.DIAMOND, Number.THREE), "3다이아몬드"),
                Arguments.of(Card(Suit.CLUB, Number.FOUR), "4클로버"),
                Arguments.of(Card(Suit.SPADE, Number.FIVE), "5스페이드"),
                Arguments.of(Card(Suit.HEART, Number.SIX), "6하트"),
                Arguments.of(Card(Suit.DIAMOND, Number.SEVEN), "7다이아몬드"),
                Arguments.of(Card(Suit.CLUB, Number.EIGHT), "8클로버"),
                Arguments.of(Card(Suit.SPADE, Number.NINE), "9스페이드"),
                Arguments.of(Card(Suit.HEART, Number.TEN), "10하트"),
                Arguments.of(Card(Suit.DIAMOND, Number.JACK), "J다이아몬드"),
                Arguments.of(Card(Suit.CLUB, Number.QUEEN), "Q클로버"),
                Arguments.of(Card(Suit.SPADE, Number.KING), "K스페이드")
            )
        }
    }
}
