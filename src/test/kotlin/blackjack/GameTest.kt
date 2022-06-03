package blackjack

import blackjack.game.Game
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameTest {

    private val game = Game()

    @Test
    fun `플레이어는 (,)로 구분하여 여러명이 게임에 참여할 수 있다`() {
        // given
        val playerRequest = "pug,jason,pobi"

        // when
        val result = game.enrollPlayers(playerRequest)

        // then
        assertThat(result.players).hasSize(3)
        assertThat(result.players).extracting("name")
            .containsExactly("pug", "jason", "pobi")
    }

    @Test
    fun `카드 숫자의 합이 21을 초과하면 패배한다`() {
        TODO()
    }

    @Test
    fun `카드 숫자의 합이 21을 넘지 않을 경우 얼마든지 카드를 계속 뽑을 수 있다`() {
        TODO()
    }

    @Test
    fun `카드 숫자의 합이 21을 넘지 않으면서 21에 가깝게 만들면 승리한다`() {
        TODO()
    }
}
