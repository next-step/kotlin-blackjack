package blackjack.domain.state

import blackjack.domain.CLUBS_ACE
import blackjack.domain.CLUBS_TEN
import blackjack.domain.CLUBS_TWO
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandsTest {
    @Test
    fun `단일 카드 점수 계산 2가 주어지면 2점을 반환한다`() {
        val hands = Hands(listOf(CLUBS_TWO))
        hands.score() shouldBe 2
    }

    @Test
    fun `일반 카드 점수 계산 2와 10이 주어지면 12점을 반환한다`() {
        val hands = Hands(listOf(CLUBS_TWO, CLUBS_TEN))
        hands.score() shouldBe 12
    }

    @Test
    fun `단일 ACE 점수 계산 ACE가 주어지면 11점을 반환한다`() {
        val hands = Hands(listOf(CLUBS_ACE))
        hands.score() shouldBe 11
    }

    @Test
    fun `블랙잭 점수 계산 ACE와 10이 주어지면 21점을 반환한다`() {
        val hands = Hands(listOf(CLUBS_ACE, CLUBS_TEN))
        hands.score() shouldBe 21
    }

    @Test
    fun `여러 ACE 점수 계산 ACE, 10, ACE가 주어지면 12점을 반환한다`() {
        val hands = Hands(listOf(CLUBS_ACE, CLUBS_TEN, CLUBS_ACE))
        hands.score() shouldBe 12
    }
}
