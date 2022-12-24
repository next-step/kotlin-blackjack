package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameTest {
    @Test
    fun `게임이 생성되면 딜러는 각 플에이어에게 2장의 카드 전달하고 본인 자신도 2장의 카드를 가진다`() {
        val firstGamePlayer = GamePlayer("고니")
        val secondGamePlayer = GamePlayer("아귀")

        val players = Players(listOf(firstGamePlayer, secondGamePlayer))
        val dealer = GameDealer()
        val game = Game(players, dealer)
        assertThat(game.players.value.all { it.cards.size == INITIAL_CARDS_COUNT }).isTrue
        assertThat(game.dealer.cards.size).isEqualTo(INITIAL_CARDS_COUNT)
    }
}
