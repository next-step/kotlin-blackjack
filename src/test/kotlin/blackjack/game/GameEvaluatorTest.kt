package blackjack.game

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit
import blackjack.dealer.Dealer
import blackjack.player.Player
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameEvaluatorTest : BehaviorSpec({

    Given("딜러와 플레이어들이 존재할 때") {
        val dealer = Dealer("딜러")
        val player1 = Player("pobi")
        val players = listOf(player1)

        When("딜러가 카드를 받았고 점수가 낮을 때") {
            dealer.addCard(Card(CardNumber.FIVE, CardSuit.HEART))
            dealer.addCard(Card(CardNumber.THREE, CardSuit.SPADE)) // 8
            player1.addCard(Card(CardNumber.NINE, CardSuit.CLUB))
            player1.addCard(Card(CardNumber.EIGHT, CardSuit.HEART)) // 17
            GameEvaluator.evaluate(dealer, players)

            Then("딜러의 점수가 플레이어1보다 낮아야 한다") {
                val dealerResult = GameEvaluator.getMatchResult[dealer.name]
                val player1Result = GameEvaluator.getMatchResult[player1.name]
                dealerResult!!.printDealerResult() shouldBe "0승 0무 1패"
                player1Result!!.printPlayerResult() shouldBe "승"
            }
        }
    }
})
