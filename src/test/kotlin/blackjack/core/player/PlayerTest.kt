package blackjack.core.player

import blackjack.core.amount.BettingAmount
import blackjack.core.card.Card
import blackjack.core.card.Denomination
import blackjack.core.card.Suit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `블랙잭 상태에서 Profit이 150퍼센트로 반환되는지 테스트`() {
        val player = Player(Name("test"), BettingAmount(1000))
        player.draw(Card(Denomination.ACE, Suit.CLUBS))
        player.draw(Card(Denomination.KING, Suit.HEARTS))

        player.checkBlackJack() shouldBe true
        player.getBettingAmount().amount shouldBe 1500
    }

    @Test
    fun `블랙잭이 아닌 상태에서 Profit이 150퍼센트로 반환되는지 테스트`() {
        val player = Player(Name("test"), BettingAmount(1000))
        player.draw(Card(Denomination.TWO, Suit.CLUBS))
        player.draw(Card(Denomination.KING, Suit.HEARTS))

        player.checkBlackJack() shouldBe false
        player.getBettingAmount().amount shouldBe 1000
    }
}
