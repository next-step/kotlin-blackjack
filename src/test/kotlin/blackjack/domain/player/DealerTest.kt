package blackjack.domain.player

import blackjack.domain.card.BlackJackCard
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import blackjack.domain.state.ResultState
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
    fun `딜러와 게이머의 무승부를 확인한다`() {
        val dealer = Dealer()
        dealer.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_10))
        dealer.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_9))

        val user2 = GameUser("B")
        user2.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_10))
        user2.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_9))
        dealer.compareGetResultOpponent(user2) shouldBe ResultState.PUSH
    }

    @Test
    fun `딜러가 오버포인트 시 유저는 승리한다`() {
        val dealer = Dealer()
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_10))
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_9))
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_6))

        val user1 = GameUser("A")
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_10))
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_6))

        dealer.compareGetResultOpponent(user1) shouldBe ResultState.WIN
    }

    @Test
    fun `딜러와 게이머가 모두 블랙잭인 경우 무승부`() {
        val dealer = Dealer()
        dealer.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_A))
        dealer.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_J))

        val user2 = GameUser("B")
        user2.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_A))
        user2.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_K))
        dealer.compareGetResultOpponent(user2) shouldBe ResultState.PUSH
    }
}
