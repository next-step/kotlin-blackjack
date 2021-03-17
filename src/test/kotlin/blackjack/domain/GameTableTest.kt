package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GameTableTest {
    @DisplayName("첫 번째 라운드인 경우 모든 플레이어가 카드를 2개씩 받음")
    @Test
    fun proceedFirstRound() {
        val gameTable = GameTable(listOf(createPlayer("pobi"), createPlayer("jason")))

        gameTable.proceedFirstRound()

        assertThat(gameTable.players).allMatch { it.hands.size == 2 }
    }
}
