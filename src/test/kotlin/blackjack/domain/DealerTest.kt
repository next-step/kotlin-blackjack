package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {
    @DisplayName("딜러는 총 52장의 카드 덱을 가지고 게임을 시작해야 한다.")
    @Test
    fun deck() {
        assertThat(Dealer().deck).isEqualTo(52)
    }
}
