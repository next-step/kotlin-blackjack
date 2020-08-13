package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun is_over_16() {
        val dealer = Dealer()
        dealer.addCard(Card("♠10", 10))
        dealer.addCard(Card("♠7", 7))

        assertThat(dealer.isOver16()).isTrue()
    }
}
