package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 시작과 동시에 두 장의 카드를 가지고 있는다`() {
        val dealer = Dealer()
        val player = Player(dealer)

        player.cardSet.size shouldBe 2
    }
}
