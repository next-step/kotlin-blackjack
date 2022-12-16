package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {

    private val fakeDeck = Deck()

    @Test
    fun `딜러라는 이름을 가진다`() {
        Dealer().name.value shouldBe "딜러"
    }

    @Test
    fun `딜러는 카드를 받을 수 있다`() {
        val dealer = Dealer().initialCard(fakeDeck)
        dealer.cards.count() shouldBe 2
        val dealer2 = Dealer().hit(fakeDeck)
        dealer2.cards.count() shouldBe 1
    }
}
