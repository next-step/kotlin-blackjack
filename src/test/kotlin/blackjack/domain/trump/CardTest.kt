package blackjack.domain.trump

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardTest {

    @ParameterizedTest
    @EnumSource(Pattern::class)
    fun `패턴에 관계없이 숫자가 ACE이면 ACE라고 판단한다`(pattern: Pattern) {
        // given
        val card = Card(pattern, Number.ACE)

        // then
        assertThat(card.isAce()).isTrue
    }
}
