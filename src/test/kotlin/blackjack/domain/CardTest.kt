package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CardTest {
    @Test
    internal fun `Card가 Ace이면 isAce=true`() {
        assertThat(SPADE_ACE.isAce).isTrue
    }

    @Test
    internal fun `Card가 Ace가 아니면 isAce=false`() {
        assertThat(SPADE_TWO.isAce).isFalse
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 3, 5, 7, 9, 10])
    internal fun `가지고 있는 카드의 합이 10이하면 ACE의 값을 11로 취급한다`(sumValue: Int) {
        val actual = SPADE_ACE.getAceNumberValue(sumValue)
        assertThat(actual).isEqualTo(11)
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 13, 16, 20])
    internal fun `가지고 있는 카드의 합이 10초과이면 ACE의 값을 1로 취급한다`(sumValue: Int) {
        val actual = SPADE_ACE.getAceNumberValue(sumValue)
        assertThat(actual).isEqualTo(1)
    }
}
