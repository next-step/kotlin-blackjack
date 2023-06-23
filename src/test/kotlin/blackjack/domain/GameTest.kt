package blackjack.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {

    private lateinit var game: Game

    @BeforeEach()
    fun setUp() {
        game = Game()
    }

    @Test
    fun `게임이 시작되면 플레이어에게 카드를 2장씩 나눠준다`() {}
}
