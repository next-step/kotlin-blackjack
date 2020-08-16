package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    @DisplayName("카드 목록을 반환한다")
    fun `cardList`() {
        val dealer = Dealer()
        assertThat(dealer.cards).isNotNull
    }

    @Test
    @DisplayName("카드 한장을 반환한다")
    fun `draw`() {
        val dealer = Dealer()
        assertThat(dealer.draw()).isNotEqualTo(dealer.draw())
    }

}