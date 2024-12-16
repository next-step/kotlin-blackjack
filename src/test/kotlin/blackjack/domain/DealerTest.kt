package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 유저를 생성`() {
        val dealer = Dealer()
        dealer.getName() shouldBe "딜러"
    }

    @Test
    fun `포인트 16 인 경우 추가 카드를 받아야 한다`() {
        val dealer = Dealer()
        dealer.addCard(BlackJackCard(CardType.SPADE, CardInfo.CARD_10))
        dealer.addCard(BlackJackCard(CardType.SPADE, CardInfo.CARD_6))
        dealer.isDoneGame() shouldBe false
    }

    @Test
    fun `포인트 17 인 경우 추가 카드를 받지 않는다`() {
        val dealer = Dealer()
        dealer.addCard(BlackJackCard(CardType.SPADE, CardInfo.CARD_10))
        dealer.addCard(BlackJackCard(CardType.SPADE, CardInfo.CARD_7))
        dealer.isDoneGame() shouldBe true
    }
}
