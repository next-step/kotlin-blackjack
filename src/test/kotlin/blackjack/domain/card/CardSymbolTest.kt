package blackjack.domain.card

import blackjack.domain.game.Game.Companion.ACE_MAX_NUMBER
import blackjack.domain.game.Game.Companion.ACE_MIN_NUMBER
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CardSymbolTest {

    @ParameterizedTest
    @CsvSource(
        // given
        "TWO, 2",
        "THREE, 3",
        "FOUR, 4",
        "FIVE, 5",
        "SIX, 6",
        "SEVEN, 7",
        "EIGHT, 8",
        "NINE, 9",
        "TEN, 10",
    )
    fun `2~9 까지의 숫자의 값은 카드 숫자를 기본으로 한다`(symbol: CardSymbol, value: Int) {
        // when, then
        assertThat(symbol.count()).isEqualTo(value)
    }

    @ParameterizedTest
    @CsvSource(
        // given
        "KING, 10",
        "QUEEN, 10",
        "JACK, 10",
    )
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
    }
}
