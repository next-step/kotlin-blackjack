package blackjack.game

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit
import blackjack.dealer.Dealer
import blackjack.player.Player
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class GameEvaluatorTest : BehaviorSpec({
    Given("딜러와 플레이어들이 존재할 때") {
        var dealer = Dealer()
        var player1 = Player("pobi")
        val players = listOf(player1)

        When("딜러가 카드를 받았고 점수가 낮을 때") {
            dealer.addCard(Card(CardNumber.FIVE, CardSuit.HEART))
            dealer.addCard(Card(CardNumber.THREE, CardSuit.SPADE)) // 8
            player1.addCard(Card(CardNumber.NINE, CardSuit.CLUB))
            player1.addCard(Card(CardNumber.EIGHT, CardSuit.HEART)) // 17
            val gameEvaluator = GameEvaluator()
            val gameResult = gameEvaluator.evaluate(dealer, players)

            Then("딜러의 점수가 플레이어1보다 낮아야 한다") {
                val dealerResult = gameResult.getResultForPlayer(dealer.name)
                val player1Result = gameResult.getResultForPlayer(player1.name)

                dealerResult!!.printDealerResult() shouldBe "0승 0무 1패"
                player1Result!!.printPlayerResult() shouldBe "승"
            }
        }

        When("딜러와 플레이어의 카드를 다양하게 변경할 때") {
            val gameEvaluator = GameEvaluator()
            forAll(
                row(
                    Card(CardNumber.TEN, CardSuit.HEART),
                    Card(CardNumber.NINE, CardSuit.SPADE), // 19
                    Card(CardNumber.EIGHT, CardSuit.CLUB),
                    Card(CardNumber.SEVEN, CardSuit.DIAMOND), // 15
                    "1승 0무 0패",
                    "패"
                ),
                row(
                    Card(CardNumber.FIVE, CardSuit.HEART),
                    Card(CardNumber.FOUR, CardSuit.SPADE), // 9
                    Card(CardNumber.TEN, CardSuit.CLUB),
                    Card(CardNumber.NINE, CardSuit.DIAMOND), // 19
                    "0승 0무 1패",
                    "승"
                )
            ) { dealerCard1, dealerCard2, playerCard1, playerCard2, expectedDealerResult, expectedPlayerResult ->
                // 새로운 객체를 생성
                dealer = Dealer()
                player1 = Player("pobi")

                dealer.addCard(dealerCard1)
                dealer.addCard(dealerCard2)
                player1.addCard(playerCard1)
                player1.addCard(playerCard2)

                val gameResult = gameEvaluator.evaluate(dealer, players)

                val dealerResult = gameResult.getResultForPlayer(dealer.name)
                val player1Result = gameResult.getResultForPlayer(player1.name)

                dealerResult!!.printDealerResult() shouldBe expectedDealerResult
                player1Result!!.printPlayerResult() shouldBe expectedPlayerResult
            }
        }
    }
})
