package blackjack.model

import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `플레이어 두 명의 이름을 받아 게임을 생성한다`() {
        val game = BlackjackGame(listOf("jason", "pobi"))

        game.shouldBeInstanceOf<BlackjackGame>()
    }
}
