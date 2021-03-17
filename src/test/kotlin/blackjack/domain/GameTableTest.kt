package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GameTableTest {
    @DisplayName("첫 번째 라운드인 경우 모든 플레이어가 카드를 2개씩 받음")
    @Test
    fun proceedFirstRound() {
        val gameTable = GameTable(createPlayers("pobi", "jason"))

        gameTable.proceedFirstRound()

        assertThat(gameTable.players.players).allMatch { it.hands.cards.size == 2 }
    }

    @DisplayName("모든 플레이어가 카드를 받을 수 없을 때 까지 진행")
    @Test
    fun proceedRemainingRound() {
        val gameTable = GameTable(createPlayers("pobi", "jason"))

        gameTable.proceedRemainingRound()

        assertThat(gameTable.players.players.map { it.hands })
    }
}
