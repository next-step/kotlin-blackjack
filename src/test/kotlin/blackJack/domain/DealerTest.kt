package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun make_dealer() {
        val dealer = Dealer()

        assertThat(dealer.deck.cards).isNotEmpty()
        assertThat(dealer.name).isEqualTo("딜러")
        assertThat(dealer.hands).isEmpty()
    }
}
