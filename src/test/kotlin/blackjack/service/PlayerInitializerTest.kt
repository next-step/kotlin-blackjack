package blackjack.service

import blackjack.service.PlayerInitializer.fromString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerInitializerTest {
    @Test
    internal fun `플레이어 이름은 쉼표로 구분한다`() {
        val players = fromString("pobi,jason")
        val playerNames = players.map { it.name }
        assertThat(playerNames).containsExactly("pobi", "jason")
    }
}
