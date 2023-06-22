package blackjack

import blackjack.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 카드들을 가질 수 있다`() {
        val player1 = Player("사람1")
        player1.drawCard()
        assertThat(player1.countCards()).isEqualTo(1)
    }
}
