package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GameTest {
    @DisplayName("게임이 생성되면 딜러는 각 플에이어에게 2장의 카드를 나눠준다.")
    @Test
    fun initialize() {
        val firstPlayer = Player("고니")
        val secondPlayer = Player("아귀")

        val players = Players(listOf(firstPlayer, secondPlayer))
        val dealer = Dealer()
        val game = Game(players, dealer)
        assertThat(game.players.value.all { it.cards.size == INITIAL_CARDS_COUNT }).isTrue
    }
}
