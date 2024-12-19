package blackjack.domain.state

import blackjack.domain.CLUBS_ACE
import blackjack.domain.CLUBS_TEN
import blackjack.domain.CLUBS_TWO
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAnyOf
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class ReadyTest {

    @Test
    fun `Ready 생성 테스트`() {
        val state = Ready()
        val actual = state.draw(CLUBS_TWO)
        actual.shouldBeInstanceOf<Ready>()
        actual.hands shouldContain CLUBS_TWO
    }

    @Test
    fun hit() {
        val state = Ready(CLUBS_TWO)
        val actual = state.draw(CLUBS_TWO)
        actual.shouldBeInstanceOf<Hit>()
        actual.hands.shouldContainAnyOf(CLUBS_TWO, CLUBS_TEN)
    }

    @Test
    fun blackjack() {
        val state = Ready(CLUBS_ACE)
        val actual = state.draw(CLUBS_TEN)
        actual.shouldBeInstanceOf<Blackjack>()
        actual.hands.shouldContainAnyOf(CLUBS_TWO, CLUBS_TEN)
    }

}
