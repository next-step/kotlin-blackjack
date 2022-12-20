package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameTest {
    @Test
    fun `게임이 생성되면 딜러는 각 플에이어에게 2장의 카드 전달하고 본인 자신도 2장의 카드를 가진다`() {
        val firstGamePlayer = GamePlayer("고니")
        val secondGamePlayer = GamePlayer("아귀")

        val gamePlayers = GamePlayers(listOf(firstGamePlayer, secondGamePlayer))
        val dealer = Dealer()
        val game = Game(gamePlayers, dealer)
        assertThat(game.gamePlayers.value.all { it.cards.size == INITIAL_CARDS_COUNT }).isTrue
        assertThat(game.dealer.cards.size).isEqualTo(INITIAL_CARDS_COUNT)
    }

    @Test
    fun `딜러와 플레이어가 블랙잭을 완성하였을 시 무승부(push)`() {
        val player = FakePlayer("고니", sumCards = 21, blackjack = true)
        val dealer = FakeDealer()
        val game = Game(GamePlayers(player), dealer)
        assertThat(game.gamePlayers.value.all { it.cards.size == INITIAL_CARDS_COUNT }).isTrue
        assertThat(game.dealer.cards.size).isEqualTo(INITIAL_CARDS_COUNT)
    }
}
