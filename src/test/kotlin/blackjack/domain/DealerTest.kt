package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카트를 뽑을 때마다 전체 장수가 줄어든다`() {
        val dealer = Dealer()
        val oldSize = dealer.cardsSize

        dealer.drawCard()

        dealer.cardsSize shouldBe oldSize - 1
    }
}
