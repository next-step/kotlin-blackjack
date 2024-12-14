package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackJackCardTest {
    @Test
    fun `카드 5를 생성하여 포인트를 확인한다`() {
        val card = BlackJackCard(CardType.SPADE, CardInfo.CARD_5)
        card.isAceCard() shouldBe false
        card.getPoint() shouldBe 5
    }
}
