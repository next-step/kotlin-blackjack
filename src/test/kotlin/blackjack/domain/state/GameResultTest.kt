package blackjack.domain.state

import blackjack.domain.card.BlackJackCard
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import blackjack.domain.player.Dealer
import blackjack.domain.player.GameUser
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `딜러의 승리로 수익을 확인한다`() {
        val dealer = Dealer()
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_10))
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_9))

        val user1 = GameUser("A", 100)
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_10))
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_6))

        val gameResult = GameResult(dealer, listOf(user1))
        gameResult.dealerBalance shouldBe 100
    }

    @Test
    fun `유저의 블랙잭 승리로 딜러의 수익을 확인한다`() {
        val dealer = Dealer()
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_10))
        dealer.cards.add(BlackJackCard(CardType.HEART, CardNumber.CARD_9))

        val user1 = GameUser("A", 100)
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_10))
        user1.cards.add(BlackJackCard(CardType.SPADE, CardNumber.CARD_A))

        val gameResult = GameResult(dealer, listOf(user1))
        gameResult.dealerBalance shouldBe -150
    }
}
