package blackjack.domain

import blackjack.InputView
import blackjack.ResultView
import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.model.Bet
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

internal class GameTest {
    @Test
    fun `게임이 생성되면 딜러는 각 플에이어에게 2장의 카드 전달하고 본인 자신도 2장의 카드를 가진다`() {
        val firstGamePlayer = GamePlayer("고니")
        val secondGamePlayer = GamePlayer("아귀")

        val players = Players(listOf(firstGamePlayer, secondGamePlayer))
        val dealer = GameDealer()
        val game = Game(players, dealer)
        assertThat(game.players.value.all { it.play.cards.size == INITIAL_CARDS_COUNT }).isTrue
        assertThat(game.dealer.play.cards.size).isEqualTo(INITIAL_CARDS_COUNT)
    }

    @Test
    fun `각 플에이어에게 2장의 카드 전달하지 않고 게임을 시작하면 에러가 발생한다`() {
        val firstGamePlayer = GamePlayer("고니")
        val secondGamePlayer = FakePlayer("아귀", play = FakeGamePlay(readyToPlay = false), bet = Bet(1000))

        val players = Players(listOf(firstGamePlayer, secondGamePlayer))
        val dealer = GameDealer()
        val game = Game(players, dealer)
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { game.playPlayers(InputView::shouldHit, ResultView::printPlayerCards) }
            .withMessage("모든 플레이어가 게임을 시작할 준비가 되어야 합니다.")
    }

    @Test
    fun `딜러가 2장의 카드 받지 않고 게임을 시작하면 에러가 발생한다`() {
        val firstGamePlayer = GamePlayer("고니")
        val secondGamePlayer = GamePlayer("아귀")

        val players = Players(listOf(firstGamePlayer, secondGamePlayer))
        val dealer = FakeDealer(play = FakeGamePlay(readyToPlay = false))
        val game = Game(players, dealer)
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { game.playDealer { } }
            .withMessage("딜러가 게임을 시작할 준비가 되어야 합니다.")
    }
}
