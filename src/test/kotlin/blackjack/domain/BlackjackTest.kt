package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackjackTest {

    @Test
    fun `blackjack은 player와 deck을 가진다`() {
        val blackjack = Blackjack(listOf(Player("han")))

        assertThat(blackjack.players).hasSize(1)
    }

    @Test
    fun `han은 카드를 한 장 뽑을 수 있다`() {
        val player = Player("han")
        val blackjack = Blackjack(listOf(player))

        blackjack.drawingCard(player)

        assertThat(player.cards).hasSize(1)
    }

    @Test
    fun `han은 카드를 두 장 뽑을 수 있다`() {
        val player = Player("han")
        val blackjack = Blackjack(listOf(player))

        blackjack.drawingCards(player, 2)

        assertThat(player.cards).hasSize(2)
    }
}
