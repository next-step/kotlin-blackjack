package blackjack.domain.state

import blackjack.domain.SPADES_ACE
import blackjack.domain.SPADES_TEN
import blackjack.domain.SPADES_TWO
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

internal class BeginTest {
    @Test
    internal fun `시작 상태에서 카드를 두 장 받았을때 21점 미만이면 히트 상태가 된다`() {
        val begin = Begin()
        val actual = begin.drawInitialHands(SPADES_TWO, SPADES_TEN)
        actual.shouldBeInstanceOf<Hit>()
    }

    @Test
    internal fun `시작 상태에서 카드를 두 장 받았을때 21이면 블랙잭 상태가 된다`() {
        val begin = Begin()
        val actual = begin.drawInitialHands(SPADES_ACE, SPADES_TEN)
        actual.shouldBeInstanceOf<Blackjack>()
    }
}
