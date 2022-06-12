package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DenominationTest {

    @Test
    fun `Denomination 은 카드 이름과 스코어를 갖는다`() {
        assertThat(Denomination.ACE.label).isEqualTo("Ace")
        assertThat(Denomination.ACE.score).isEqualTo(1)
        assertThat(Denomination.ACE.extraScore).isEqualTo(listOf(1, 11))
    }
}
