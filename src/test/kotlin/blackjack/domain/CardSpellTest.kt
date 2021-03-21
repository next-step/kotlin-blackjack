package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardSpellTest {
    @Test
    fun `에이스가 맞으면 true 아니면 false`() {
        assertThat(CardSpell.ACE.isAce()).isTrue()
        assertThat(CardSpell.TWO.isAce()).isFalse()
        assertThat(CardSpell.JACK.isAce()).isFalse()
    }
}
