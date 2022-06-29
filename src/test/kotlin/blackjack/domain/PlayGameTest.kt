package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

internal class PlayGameTest {
    @Test
    internal fun `start()를 실행하면 중복되지 않은 2장의 카드를 받는다`() {
        val player = Player("name")
        PlayGame().start(player)

        assertThat(player.cards.distinct()).hasSize(2)
    }

    @Test
    internal fun `hit()을 실행하면 1장의 카드를 받는다`() {
        val player = Player("name")
        PlayGame().hit(player)

        assertThat(player.cards).hasSize(1)
    }

    // @Test
    internal fun `y를 입력받는 경우 card 개수가 1개 더 늘어난다`() {
        val input = "y"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val player = Player("name")
        PlayGame().hitOrStandMultipleTimes(player)
        assertThat(player.cards).hasSize(1)
    }
}
