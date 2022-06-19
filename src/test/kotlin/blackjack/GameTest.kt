package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Game
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `game에 입장하면 player가 반환된다`() {
        val players = Game(Dealer()).enter("서정국")
        assertThat(players[0].name).isEqualTo("서정국")
    }

    @Test
    fun `게임에서 카드를 돌리면 플레이어가 카드를 받는다`() {
        val game = Game(Dealer())
        val players = game.enter("서정국")
        game.shareCards(players)

        assertThat(players[0].cards.size).isEqualTo(2)
    }
}
