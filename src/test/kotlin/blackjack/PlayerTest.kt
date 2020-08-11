package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {
    @DisplayName("플레이어가 생성되면 카드 두장을 가진다.")
    @Test
    fun `when created player than have two cards`() {
        val players = Player("harry")
        assertThat(players.getCards().size).isEqualTo(2)
    }
}
