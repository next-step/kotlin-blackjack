package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `플레이어는 이름과 카드들을 가진다`() {
        val player = Player("laco", Cards.empty())
        assertAll(
            { assertThat(player.name).isEqualTo("laco") },
            { assertThat(player.cards).isEqualTo(Cards.empty()) }
        )
    }
}
