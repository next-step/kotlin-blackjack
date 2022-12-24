package blackjack.domain

import blackjack.domain.Number.ACE
import blackjack.domain.Number.FIVE
import blackjack.domain.Sharp.SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class CardTest {

    @ParameterizedTest
    @CsvSource(value = ["ACE:12", "FIVE:5"], delimiter = ':')
    internal fun `카드 점수의 합계를 반환한다`(number: Number, expected: Int) {
        // given
        val card = Card(number, SPADE)

        // when
        val sumAllScore = card.sumAllScore()

        // then
        assertThat(sumAllScore).isEqualTo(expected)
    }

    @Test
    internal fun `ACE 카드면 true를 반환한다`() {
        // given
        val card = Card(ACE, SPADE)

        // when, then
        assertThat(card.isAce()).isTrue
    }

    @Test
    internal fun `ACE 카드가 아니면 false를 반환한다`() {
        // given
        val card = Card(FIVE, SPADE)

        // when, then
        assertThat(card.isAce()).isFalse
    }

    @ParameterizedTest
    @ValueSource(strings = ["QUEEN", "JACK", "KING"])
    internal fun `값이 10인 숫자를 가지고 있으면 true를 반환한다`(number: Number) {
        // given
        val card = Card(number, SPADE)

        // when, then
        assertThat(card.isTenNumberCard()).isTrue
    }

    @ParameterizedTest
    @ValueSource(strings = ["ACE", "TWO", "THREE"])
    internal fun `값이 10이 아닌 숫자를 가지고 있으면 false를 반환한다`(number: Number) {
        // given
        val card = Card(number, SPADE)

        // when, then
        assertThat(card.isTenNumberCard()).isFalse
    }
}
