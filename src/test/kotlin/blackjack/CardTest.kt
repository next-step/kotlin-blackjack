package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    internal fun `카드 이름이 객체로 잘 전환되는지 확인`() {
        val actual = Card.of("A스페이드")
        val expected = Card(Suit.SPADE, Rank.ACE)
        actual shouldBe expected
    }
}
