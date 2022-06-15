package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 카드를 줄 수 있다`() {
        assertThat(Dealer().give() is Card).isEqualTo(true)
    }

    @Test
    fun `딜러가 처음주는 카드는 2 장이다`() {
        assertThat(Dealer().shareCards().size).isEqualTo(2)
    }
}
