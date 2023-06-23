package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 처음에 카드 2장을 가지고 있다`() {
        val player = Player()
        player.cards.size shouldBe 2
    }
}
