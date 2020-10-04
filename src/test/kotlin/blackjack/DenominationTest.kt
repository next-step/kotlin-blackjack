package blackjack

import org.assertj.core.api.Assertions.assertThat
import domain.Denomination
import org.junit.jupiter.api.Test

class DenominationTest {

    @Test
    fun `스코어 확인`() {
        val sampleCard = Denomination.FIVE
        assertThat(Denomination.getScore(sampleCard)).isEqualTo(5)
    }
}
