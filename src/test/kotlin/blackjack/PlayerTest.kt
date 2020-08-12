package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {
    @DisplayName("플레이어가 생성되면 카드 두장을 가진다.")
    @Test
    fun `when created player than have two cards`() {
        val players = Player("harry")
        assertThat(players.getCards().getCards().size).isEqualTo(2)
    }

    @DisplayName("y를 선택하면 카드가 추가된다.")
    @Test
    fun `when select y than card is added`() {
        val player = Player("harry")
        player.selectValue("y")
        assertThat(player.getCards().getCards().size).isEqualTo(3)
    }
}
