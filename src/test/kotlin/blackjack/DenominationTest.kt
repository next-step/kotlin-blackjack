package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DenominationTest {

    @Test
    fun `숫자는 그대로 계산한다`(){
        assertThat(Denomination.getScore(Denomination.FIVE)).isEqualTo(5)
        assertThat(Denomination.getScore(Denomination.QUEEN)).isEqualTo(10)

    }

}