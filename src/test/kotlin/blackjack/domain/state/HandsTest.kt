package blackjack.domain.state

import blackjack.domain.CLUBS_ACE
import blackjack.domain.CLUBS_TEN
import blackjack.domain.CLUBS_TWO
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandsTest {

    @Test
    fun `2`(
    ) {
        val hands = Hands(listOf(CLUBS_TWO))
        hands.score() shouldBe 2

    }

    @Test
    fun `12`(
    ) {
        val hands = Hands(listOf(CLUBS_TWO, CLUBS_TEN))
        hands.score() shouldBe 12

    }

    @Test
    fun `11`(
    ) {
        val hands = Hands(listOf(CLUBS_ACE))
        hands.score() shouldBe 11

    }

    @Test
    fun `21`(
    ) {
        val hands = Hands(listOf(CLUBS_ACE, CLUBS_TEN))
        hands.score() shouldBe 21

    }

    @Test
    fun `ace12`(
    ) {
        val hands = Hands(listOf(CLUBS_ACE, CLUBS_TEN, CLUBS_ACE))
        hands.score() shouldBe 12

    }
}
