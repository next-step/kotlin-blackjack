package blackjack.domain.card

import blackjack.domain.card.State
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class StateTest {

    @Test
    fun `턴이 종료된 상태`() {
        State.BLACKJACK.isFinished shouldBe true
        State.STAY.isFinished shouldBe true
        State.BUST.isFinished shouldBe true
    }

    @Test
    fun `턴이 종료되지 않은 상태`() {
        State.BEGIN.isFinished shouldBe false
        State.HIT.isFinished shouldBe false
    }

    @Test
    fun `BLACKJACK 상태에서는 베팅한 금액의 1점5배를 받는다`() {
        State.BLACKJACK.profit(1000) shouldBe 1500
    }

    @ParameterizedTest
    @EnumSource(value = State::class, names = ["BEGIN", "HIT", "STAY", "BUST"])
    fun `BLACKJACK 이외에는 베팅한 그대로 금액을 사용한다`(state: State) {
        state.profit(1000) shouldBe 1000
    }
}