package blackjack.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.assertThrows

internal class StateTest {

    @Test
    fun `hit를 할수 없는 상태에서 카드 추가시 에러 발생`() {
        val state = NotHittableState()
        assertThrows<IllegalStateException> { state.addCard(Card.of(CardSymbol.CLOVER, CardNumber.SEVEN)) }
    }

    private class NotHittableState : State(PlayerCards()) {
        override val hittable: Boolean
            get() = false
        override val earningsRate: Double
            get() = 0.0
    }
}
