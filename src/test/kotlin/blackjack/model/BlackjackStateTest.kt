package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackStateTest {
    val players = Players(
        listOf(
            Player("jinwoo", Cards.emptyCards()),
            Player("jason", Cards.emptyCards())
        )
    )

    @Test
    fun `Players들을 생성자로 받는다`() {
        assertThat(BlackjackState(players).players).isEqualTo(players)
    }

    @Test
    fun `Players에게 초기 카드를 발급한다`() {
        val newState = BlackjackState(players).initDeal()
        assertThat(newState.players.values).hasSize(2)
        newState.players.values.forEach {
            assertThat(it.cards.values).hasSize(2)
        }
    }

    @Test
    fun `아직 종료되지 않은 플레이어를 반환한다`() {
        val state = BlackjackState(players)
        assertThat(state.findNotOverPlayer()).isEqualTo(players.values[0])

        val newState = state.stay(players.values[0])
        assertThat(newState.findNotOverPlayer()).isEqualTo(players.values[1])

        val finalState = newState.stay(players.values[1])
        org.junit.jupiter.api.assertThrows<NoSuchElementException> {
            finalState.findNotOverPlayer()
        }
    }

    @Test
    fun `특정 플레이어에게 카드를 지급한다`() {
        val state = BlackjackState(players)
        assertThat(state.players.values[0].cards.values).hasSize(0)

        val newState = state.hit(state.players.values[0])
        assertThat(newState.players.values[0].cards.values).hasSize(1)
        assertThat(newState.players.values[1]).isSameAs(state.players.values[1])
    }

    @Test
    fun `모든 플레이어가 게임이 종료되었는지 여부를 판단한다`() {
        val state = BlackjackState(players)
        assertThat(state.isAllPlayersOver()).isFalse()

        val newState = players.values.fold(state) { currState, player -> currState.stay(player) }
        assertThat(newState.isAllPlayersOver()).isTrue()
    }

    @Test
    fun `이름으로 플레이어를 찾는다`() {
        val state = BlackjackState(players)
        assertThat(state.findPlayer("jason")).isNotNull
        org.junit.jupiter.api.assertThrows<NullPointerException> {
            state.findPlayer("notExists")
        }
    }
}
