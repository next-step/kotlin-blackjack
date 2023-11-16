@file:Suppress("NonAsciiCharacters")

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `게임을 시작하면 각 플레이어는 두 장의 카드를 지급 받는다`() {
        val player1 = Player("a")
        val player2 = Player("b")
        val game = Game(
            players = listOf(player1, player2),
            deck = Deck(),
        )

        game.start()

        assertThat(player1.cards).hasSize(2)
        assertThat(player2.cards).hasSize(2)
    }
}
