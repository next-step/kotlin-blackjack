package blackjack.domain.player

import blackjack.domain.Dealer
import blackjack.domain.SPADES_TEN
import blackjack.domain.SPADES_TWO
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    internal fun `딜러는 카드 점수 합계가 17점 이상이면 히트할 수 없다`() {
        val sut = Dealer()
        sut.initializeHands(SPADES_TEN, SPADES_TEN)
        sut.canHit shouldBe false
    }

    @Test
    internal fun `딜러는 카드 점수 합계가 17점 미만이면 히트할 수 있다`() {
        val sut = Dealer()
        sut.initializeHands(SPADES_TWO, SPADES_TEN)
        sut.canHit shouldBe true
    }
}
