package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `한 장 더 받기 가능 여부`() {
        val dealer = Dealer().apply {
            requestCard(Card(Denomination.ACE to Shape.DIAMOND))
            requestCard(Card(Denomination.FIVE to Shape.DIAMOND))
        }

        assertThat(dealer.checkIfExtraCardOrNot()).isTrue()
    }
}
