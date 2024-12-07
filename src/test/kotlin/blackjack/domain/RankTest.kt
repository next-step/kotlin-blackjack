package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `Ace(숫자1)는 1 또는 11로 계산할 수 있다`() {
        Rank.ACE.getNumber() shouldBe listOf(1, 11)
    }
}
