package blackjack.domain.rule

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

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
}