package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `카드 딜링 테스트`() {
        val dealer = Dealer()
        val cards = dealer.dealingTwoCards()
        assertThat(cards).hasSize(2)
        assertThat(cards[0]).isNotEqualTo(cards[1])
    }

    @Test
    fun `카드가 다 소진되면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            val dealer = Dealer()
            repeat(26) { dealer.dealingTwoCards() }
        }
    }
}
