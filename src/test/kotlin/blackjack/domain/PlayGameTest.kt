package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PlayGameTest {
    @Test
    internal fun `start()를 실행하면 중복되지 않은 2장의 카드를 받는다`() {
        val player = Player("name")
        PlayGame(listOf(player)).start()

        assertThat(player.cards.distinct()).hasSize(2)
    }
}
