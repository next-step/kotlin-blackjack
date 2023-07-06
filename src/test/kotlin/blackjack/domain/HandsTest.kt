package blackjack.domain

import blackjack.CLUBS_TWO
import blackjack.SPADES_ACE
import blackjack.SPADES_FOUR
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandsTest {
    @Test
    fun `처음에는 2장의 카드를 받는다`() {
        val hands = Hands(listOf(CLUBS_TWO, SPADES_ACE))

        hands.cards.size shouldBe 2
    }

    @Test
    fun `처음에 2장을 받지 않으면 예외를 발생한다`() {
        shouldThrow<IllegalArgumentException> { Hands(listOf(CLUBS_TWO, SPADES_ACE, SPADES_FOUR)) }
        shouldThrow<IllegalArgumentException> { Hands(listOf(CLUBS_TWO)) }
    }
}