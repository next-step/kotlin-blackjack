package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class ChallengerTest {
    @Test
    internal fun `도전자는 카드 점수 합계가 21점 이하면 히트 가능하다`() {
        val sut = Challenger("A")
        sut.receive(Card.of(CardRank.ACE))
        sut.canHit shouldBe true
        sut.receive(Card.of(CardRank.QUEEN))
        sut.canHit shouldBe true
        sut.receive(Card.of(CardRank.KING))
        sut.canHit shouldBe false
    }

    @Test
    internal fun `도전자가 스테이(카드를 받지 않음)를 하면 더이상 히트 할 수 없다`() {
        val sut = Challenger("A")
        sut.canHit shouldBe true
        sut.stay()
        sut.canHit shouldBe false
    }
}
