package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayerFactoryTest {
    @DisplayName("플레이어 생성")
    @Test
    fun create() {
        val input = "pobi,jason"
        val users = PlayerFactory.create(input)

        assertAll(
            { assertThat(users.size).isEqualTo(2) },
            { assertThat(users.map { it.playerName }).containsExactly(PlayerName("pobi"), PlayerName("jason")) }
        )
    }
}
