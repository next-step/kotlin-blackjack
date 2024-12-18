package blackjack.domain

import blackjack.domain.card.BlackJackCard
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import blackjack.domain.player.Dealer
import blackjack.domain.player.GameUser
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 유저를 생성`() {
        val dealer = Dealer()
        dealer.name shouldBe "딜러"
    }

    @Test
    fun `포인트 16 인 경우 추가 카드를 받아야 한다`() {
        val dealer = Dealer()
        dealer.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_10))
        dealer.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_6))
        dealer.isDoneGame() shouldBe false
    }

    @Test
    fun `포인트 17 인 경우 추가 카드를 받지 않는다`() {
        val dealer = Dealer()
        dealer.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_10))
        dealer.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_7))
        dealer.isDoneGame() shouldBe true
    }

    @Test
    fun `딜러와 게이머의 승패를 확인한다`() {
        val user1 = GameUser("A")
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_10))
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_9))
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_6))

        val user2 = GameUser("B")
        user2.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_10))
        user2.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_9))
        user2.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_6))
//        user1.comparePoints(user2) shouldBe false
//        user2.comparePoints(user1) shouldBe false
    }

    @Test
    fun `딜러가 오버포인트 시 모든 사용자는 승리한다`() {
        val dealer = Dealer()
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_10))
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_9))
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_6))

        val user1 = GameUser("A")
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_10))
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_6))

//        user1.comparePoints(user2) shouldBe true
//        user2.comparePoints(user1) shouldBe false
    }

//    companion object {
//        val dealer = Dealer()
//    }
}
