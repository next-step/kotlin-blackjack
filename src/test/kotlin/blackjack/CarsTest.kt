package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CarsTest {

    @Test
    fun `카드들이 정상적으로 생성되었는지 확인`() {
        assertThat(Cards.getCards().size).isEqualTo(52)
    }
}
