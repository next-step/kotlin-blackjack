package blackjack.domain.card

import blackjack.domain.game.Game.Companion.ACE_MAX_NUMBER
import blackjack.domain.game.Game.Companion.ACE_MIN_NUMBER
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CardSymbolTest {

    @ParameterizedTest
    @MethodSource("숫자 심볼을 가지는 카드들과 예상 값")
    fun `숫자 심볼을 가지는 카드 값은 카드 숫자를 기본으로 한다`(symbol: CardSymbol, value: Int) {
        // when, then
        assertThat(symbol.count()).isEqualTo(value)
    }

    @ParameterizedTest
    @MethodSource("등급 심볼을 가지는 카드들과 예상 값")
    fun `King, Queen, Jack 의 값은 각각 10으로 계산한다`(symbol: CardSymbol, value: Int) {
        // when, then
        assertThat(symbol.count()).isEqualTo(value)
    }

    @Test
    fun `Ace 의 값은 전체 카드의 합이 21을 초과하면 1로 계산한다`() {
        // given
        val ace = CardSymbol.ACE

        // when
        val sumOfOtherCards = NUMBER_OF_EXCEED_THRESHOLD_WITH_ACE

        // when, then
        assertThat(ace.count(sumOfOtherCards)).isEqualTo(ACE_MIN_NUMBER)
    }

    @Test
    fun `Ace 의 값은 전체 카드의 합이 21을 초과하지 않으면 11로 계산한다`() {
        // given
        val ace = CardSymbol.ACE

        // when, then
        assertThat(ace.count(NUMBER_OF_LEAST_THRESHOLD_WITH_ACE)).isEqualTo(ACE_MAX_NUMBER)
    }

    companion object {
        private const val NUMBER_OF_EXCEED_THRESHOLD_WITH_ACE = 11
        private const val NUMBER_OF_LEAST_THRESHOLD_WITH_ACE = 10

        @JvmStatic
        fun `숫자 심볼을 가지는 카드들과 예상 값`() = Stream.of(
            Arguments.of(CardSymbol.TWO, 2),
            Arguments.of(CardSymbol.THREE, 3),
            Arguments.of(CardSymbol.FOUR, 4),
            Arguments.of(CardSymbol.FIVE, 5),
            Arguments.of(CardSymbol.SIX, 6),
            Arguments.of(CardSymbol.SEVEN, 7),
            Arguments.of(CardSymbol.EIGHT, 8),
            Arguments.of(CardSymbol.NINE, 9),
            Arguments.of(CardSymbol.TEN, 10)
        )

        @JvmStatic
        fun `등급 심볼을 가지는 카드들과 예상 값`() = Stream.of(
            Arguments.of(CardSymbol.KING, 10),
            Arguments.of(CardSymbol.QUEEN, 10),
            Arguments.of(CardSymbol.JACK, 10)
        )




    }
}
