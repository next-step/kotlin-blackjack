package blackjack.business.canDrawCardStrategy

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어가 카드를 뽑을 수 있는지 판단하는 전략")
class PlayerCanDrawCardStrategyTest {

    @Test
    fun `블랙잭보다 작으면 true`() {
        // given
        val playerCanDrawCardStrategy = PlayerCanDrawCardStrategy()

        // when
        val canDrawCard = playerCanDrawCardStrategy.canDrawCard(20)

        // then
        canDrawCard shouldBe true
    }

    @Test
    fun `블랙잭이상이면 false`() {
        // given
        val playerCanDrawCardStrategy = PlayerCanDrawCardStrategy()

        // when
        val canDrawCard = playerCanDrawCardStrategy.canDrawCard(21)

        // then
        canDrawCard shouldBe false
    }
}
