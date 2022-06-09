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
    fun `모든 카드가 있는 상태로 초기화된 Cards 를 필드로 갖는다`() {
        assertThat(BlackjackState.createShuffledCards().values).hasSize(52)
    }

    @Test
    fun `Players들을 생성자로 받는다`() {
        assertThat(BlackjackState(players).players).isEqualTo(players)
    }

    @Test
    fun `Players에게 초기 카드를 발급한다`() {
        val newState = BlackjackState(players).giveInitCards()
        assertThat(newState.players.values).hasSize(2)
        newState.players.values.forEach {
            assertThat(it.cards.values).hasSize(2)
        }
    }

    @Test
    fun `아직 종료되지 않은 플레이어 목록을 반환한다`() {
        val state = BlackjackState(players)
        assertThat(state.findNotOverPlayers()).hasSize(2)
        val newState = state.setGameOver(players.values[0])
        assertThat(newState.findNotOverPlayers()).hasSize(1)
    }

    @Test
    fun `특정 플레이어에게 카드를 지급한다`() {
        val state = BlackjackState(players)
        assertThat(state.players.values[0].cards.values).hasSize(0)

        val newState = state.giveCard(state.players.values[0])
        assertThat(newState.players.values[0].cards.values).hasSize(1)
        assertThat(newState.players.values[1]).isSameAs(state.players.values[1])
    }

    @Test
    fun `모든 플레이어가 게임이 종료되었는지 여부를 판단한다`() {
        val state = BlackjackState(players)
        assertThat(state.isAllPlayersGameOver()).isFalse()

        val newState = players.values.fold(state) { currState, player -> currState.setGameOver(player) }
        assertThat(newState.isAllPlayersGameOver()).isTrue()
    }
}
