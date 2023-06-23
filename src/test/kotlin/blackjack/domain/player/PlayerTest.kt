package blackjack.domain.player

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 처음 포인트가 0이다`() {
        // given
        val player = Player("goofy")
        // when
        player.getNowPoints() shouldBe 0
    }

    @Test
    fun `플레이어는 카드를 뽑을 수 있다`() {
        // given
        val player = Player("goofy")
        // when
        player.drawCard()
        // then
        player.getTotalCardSize() shouldBe 1
        player.getNowPoints() shouldNotBe 0
    }
}
