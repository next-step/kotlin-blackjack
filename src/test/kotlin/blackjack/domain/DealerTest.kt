package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    internal fun `딜러는 카드 점수 합계가 17점 이상이면 히트할 수 없다`() {
        val sut = Dealer()
        sut.canHit shouldBe true

        sut.receive(Card.of(CardRank.TEN))
        sut.receive(Card.of(CardRank.SEVEN))

        sut.canHit shouldBe false
    }
}
