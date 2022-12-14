package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    internal fun `플레이어 이름을 입력받는다`() {
        val name = "jason"
        val player = Player(name)
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    internal fun `플레이어가 가진 카드 숫자를 합친다`() {
        TODO("Not yet implemented")
    }

    @Test
    internal fun `카드 합이 21을 넘을 경우 카드를 뽑지 못한다`() {
        TODO("Not yet implemented")
    }
}
