package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CardsTest {

    @Test
    fun `카드들의 총점 계산`() {
        // given
        val cards = Cards("7", "10")

        // then
        assertThat(cards.totalScore).isEqualTo(17)
    }

    @Test
    fun `카드가 블랙잭인지 확인`() {
        // given
        val cards = Cards("A", "10")

        // then
        assertThat(cards.isBlackJack()).isTrue()
    }

    @MethodSource("cardsAndCanPlay")
    @ParameterizedTest
    fun `플레이를 더 할 수있는지 확인`(cards: Cards, canPlay: Boolean) {
        // then
        assertThat(cards.canPlay()).isEqualTo(canPlay)
    }

    companion object {
        @JvmStatic
        fun cardsAndCanPlay(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Cards("10", "10", "10"), false),
                Arguments.of(Cards("A", "10"), false),
                Arguments.of(Cards("10"), true)
            )
        }
    }
}
