package blackjack.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 총 52장 카드의 덱을 가진다`() {
        Dealer().deck.count() shouldBe 52
    }

    @RepeatedTest(5)
    fun `딜러는 덱을 셔플할 수 있다`() {
        val dealer = Dealer()
        dealer.shuffle()
        dealer.deck.list shouldNotBe Deck().cards.list
    }

    @Test
    fun `딜러는 덱에서 카드 한장을 분배할 수 있다`() {
        val dealer = Dealer()
        var count = dealer.deck.count()
        repeat(5) {
            dealer.divede()
            count--
            dealer.deck.count() shouldBe count
        }
    }
}
