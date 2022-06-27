package blackjack.domain

import blackjack.domain.user.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BlackjackTest {

    @Test
    fun `blackjack은 player와 deck을 가진다`() {
        val blackjack = Blackjack(listOf(Player("player")))

        assertThat(blackjack.players).hasSize(1)
    }

    @Test
    fun `player는 게임 시작 시 카드 0장을 가진다`() {
        val player = Player("player")
        val blackjack = Blackjack(listOf(player))

        assertThat(blackjack.players).hasSize(1)
        assertThat(blackjack.players.first().isEmptyCards()).isTrue
    }

    @Test
    fun `player는 카드를 받을 수 있다`() {
        val player = Player("player")
        val blackjack = Blackjack(listOf(player))
        blackjack.drawCard(player)

        assertThat(blackjack.players).hasSize(1)
        assertThat(blackjack.players.first().cards()).hasSize(1)
    }

    @Test
    fun `dealer와 player는 게임 시작 후 카드 2장을 받을 수 있다`() {
        val player = Player("player")
        val blackjack = Blackjack(listOf(player))
        blackjack.drawFirstCards()

        assertThat(blackjack.players).hasSize(1)
        assertThat(blackjack.dealer.cards()).hasSize(2)
        assertThat(blackjack.players.first().cards()).hasSize(2)
    }

    @Test
    fun `카드가 있으면, drawFirstCards를 호출할 수 없다`() {
        val player = Player("player")
        val blackjack = Blackjack(listOf(player))
        blackjack.drawFirstCards()
        assertThrows<IllegalStateException> { blackjack.drawFirstCards() }
    }
}
