package balckjac.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SuitTest {

    @Test
    fun `Suit 는 카드이름을 갖는다`() {
        assertThat(Suit.DIA.cardName).isEqualTo("하트(♥)")
    }
}
