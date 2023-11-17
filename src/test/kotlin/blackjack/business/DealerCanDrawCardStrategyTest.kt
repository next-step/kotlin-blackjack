package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("딜러가 카드를 뽑을 수 있는지 판단하는 전략")
class DealerCanDrawCardStrategyTest {

    @Test
    fun `16이하이면 true`() {
        // given
        val dealerCanDrawCardStrategy = DealerCanDrawCardStrategy()

        // when
        val canDrawCard = dealerCanDrawCardStrategy.canDrawCard(16)

        // then
        canDrawCard shouldBe true
    }

    @Test
    fun `17이상이면 false`() {
        // given
        val dealerCanDrawCardStrategy = DealerCanDrawCardStrategy()

        // when
        val canDrawCard = dealerCanDrawCardStrategy.canDrawCard(17)

        // then
        canDrawCard shouldBe false
    }
}
