package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `Draw를 하면 Player는 Card를 얻어야 한다`() {
        // given
        val player = Player("jay")
        val blackjackGame = BlackjackGame(Deck(), listOf(player))

        // when
        blackjackGame.draw(player)

        // then
        assertThat(player.cards.cards.size).isEqualTo(1)
    }

    @Test
    fun `시작을 하면 Player는 Card를 2장 얻어야 한다`() {
        // given
        val blackjackGame = BlackjackGame(Deck(), listOf(Player("jay")))

        // when
        blackjackGame.start()

        // then

        assertThat(blackjackGame.players[0].cards.cards.size).isEqualTo(2)
    }

}
