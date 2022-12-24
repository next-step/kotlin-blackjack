package blackjack.domain

import blackjack.domain.Number.ACE
import blackjack.domain.Number.EIGHT
import blackjack.domain.Number.FOUR
import blackjack.domain.Number.NINE
import blackjack.domain.Number.QUEEN
import blackjack.domain.Number.SIX
import blackjack.domain.Number.TWO
import blackjack.domain.Sharp.CLOVER
import blackjack.domain.Sharp.DIAMOND
import blackjack.domain.Sharp.HEART
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

internal class CardsTest {

    @Test
    internal fun `카드가 생성된다`() {
        // given
        // when
        val cards = Cards(mutableSetOf(Card(ACE, CLOVER), Card(SIX, CLOVER)))

        // when, then
        assertThat(cards.size).isEqualTo(2)
    }

    @Test
    internal fun `카드가 두장 아래면 예외가 발생한다`() {
        // given
        // when, then
        assertThatIllegalArgumentException().isThrownBy { Cards(mutableSetOf()) }
        assertThatIllegalArgumentException().isThrownBy { Cards(mutableSetOf(Card(SIX, CLOVER))) }
    }

    @Test
    internal fun `카드가 추가된다`() {
        // given
        val cards = Cards(mutableSetOf(Card(ACE, CLOVER), Card(SIX, CLOVER)))
        // when
        cards.add(Card(TWO, HEART))

        // then
        assertThat(cards.size).isEqualTo(3)
    }

    @ParameterizedTest
    @ValueSource(strings = ["KING", "QUEEN", "JACK"])
    internal fun `처음 두 카드의 합이 21이면 블랙잭이다`(secondNumber: Number) {
        // given
        val cards = Cards(mutableSetOf(Card(ACE, CLOVER), Card(secondNumber, CLOVER)))

        // when, then
        assertThat(cards.blackJack()).isTrue
    }

    @Test
    internal fun `처음 두 카드의 합이 21이 아니면 블랙잭이 아니다`() {
        // given
        val cards = Cards(mutableSetOf(Card(NINE, CLOVER), Card(EIGHT, CLOVER), Card(FOUR, CLOVER)))

        // when, then
        assertThat(cards.blackJack()).isFalse
    }

    @Test
    internal fun `중복 카드는 추가시 예외가 발생한다`() {
        // given
        val cards = Cards(mutableSetOf(Card(ACE, CLOVER), Card(SIX, CLOVER)))

        // when, then
        assertThatIllegalArgumentException().isThrownBy { cards.add(Card(SIX, CLOVER)) }
    }

    @ParameterizedTest
    @CsvSource(value = ["TWO:SIX:8", "TWO:SEVEN:9", "FIVE:FOUR:9", "FIVE:FIVE:10"], delimiter = ':')
    internal fun `점수의 합계가 반환된다`(num1: Number, num2: Number, expected: Int) {
        // given
        val cards = Cards(mutableSetOf(Card(num1, CLOVER), Card(num2, HEART)))

        // when, then
        assertThat(cards.score()).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("cardArguments")
    internal fun `ACE가 포함되면, 21에 가장 가까운 수로 1 또는 11로 계산된다`(cards: Cards, expected: Int) {
        // given
        // when, then
        assertThat(cards.score()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun cardArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Cards(mutableSetOf(Card(QUEEN, CLOVER), Card(ACE, HEART))), 21),
                Arguments.of(Cards(mutableSetOf(Card(ACE, CLOVER), Card(ACE, HEART), Card(ACE, DIAMOND))), 23),
                Arguments.of(Cards(mutableSetOf(Card(QUEEN, CLOVER), Card(QUEEN, HEART), Card(ACE, DIAMOND))), 21),
            )
        }
    }
}
