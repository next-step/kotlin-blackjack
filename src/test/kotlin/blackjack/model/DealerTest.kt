package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {

    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        dealer = Dealer(Cards.empty())
    }

    @Test
    fun `딜러는 카드들을 가진다`() {
        assertThat(dealer.cards).isEqualTo(Cards.empty())
    }
}
