package blackjack.participant.status

import blackjack.participant.BettingAmount
import blackjack.participant.Result
import org.junit.jupiter.api.Test
import io.kotest.matchers.shouldBe

class StatusTest {

    @Test
    fun `HIT 상태에서 이기면 배팅금액을 그대로 받는다`() {
        val hit = Hit()
        val result = hit.calculateBettingAmount(Result.Win, BettingAmount(1000))
        result.amount shouldBe 1000
    }

    @Test
    fun `Stand 상태에서 이기면 배팅금액을 그대로 받는다`() {
        val stand = Stand()
        val result = stand.calculateBettingAmount(Result.Win, BettingAmount(1000))
        result.amount shouldBe 1000
    }

    @Test
    fun `Bust 상태는 배팅 금액을 잃는다`() {
        val bust = Bust()
        val result = bust.calculateBettingAmount(Result.Win, BettingAmount(1000))
        result.amount shouldBe 0
    }

    @Test
    fun `블랙잭 상태에서 이기면 배팅금액의 1점 5배를 받는다`() {
        val blackjack = Blackjack()
        val result = blackjack.calculateBettingAmount(Result.Win, BettingAmount(1000))
        result.amount shouldBe 1500
    }
}
