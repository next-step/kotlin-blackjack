package blackjack.domain

import blackjack.HEARTS_KING
import blackjack.SPADES_ACE
import blackjack.SPADES_FOUR
import blackjack.SPADES_KING
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 16점 이하일 때 카드를 추가로 받을 수 있는 상태다`() {
        val hands = Hands(listOf(SPADES_KING, SPADES_FOUR))
        val dealer = Dealer(hands)

        dealer.shouldHit() shouldBe true
    }

    @Test
    fun `딜러는 17점 이상일 때 hit 하면 예외가 발생한다`() {
        val hands = Hands(listOf(SPADES_KING, HEARTS_KING))
        val dealer = Dealer(hands)

        shouldThrow<IllegalArgumentException> { dealer.hit(SPADES_ACE) }
    }
}