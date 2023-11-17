package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 카드를 한장씩 추가 한다`() {
        // given
        val dealer = Dealer()
        val cardDesk = CardDesk()

        // when
        val card = cardDesk.draw()
        dealer.addCard(card)

        // then
        dealer.cards.size shouldBe 1
    }
}
