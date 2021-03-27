package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `Dealer의 이름은 딜러로 고정된다`() {
        assertThat(Dealer().name).isEqualTo("딜러")
    }
}
