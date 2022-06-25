package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `플레이어를 상속받는 딜러가 존재한다`() {
        val dealer = Dealer()
        Assertions.assertThat(dealer.name).isEqualTo("딜러")
        Assertions.assertThat(dealer.cards).isEqualTo(Cards.emptyCards())
        Assertions.assertThat(dealer.stay).isEqualTo(false)
    }
}
