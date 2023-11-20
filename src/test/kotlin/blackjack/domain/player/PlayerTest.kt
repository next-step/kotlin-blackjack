package blackjack.domain.player

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player("cookie")
        player.name shouldBe "cookie"
    }
}


