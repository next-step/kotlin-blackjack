package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameTest {
    @Test
    fun `모든 플레이어가 카드를 받지 않는 상태가 되면 게임이 종료된다`() {
        val firstPlayer = FIRST_PLAYER
        val secondPlayer = SECOND_PLAYER

        firstPlayer.draw(Card(CardSuite.HEART, CardNumber.SIX))
        secondPlayer.stop()

        val game = Game(Players(setOf(firstPlayer, secondPlayer)))

        assertThat(game.state).isEqualTo(GameStates.END)
    }
}
