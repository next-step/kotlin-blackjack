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
        val player1 = Player("pobi", 10000)
        val dealer = Dealer()

        val players = listOf(player1)

        When("플레이어가 버스트이면") {
            dealer.addCard(Card(CardNumber.FIVE, CardSuit.HEART))
            dealer.addCard(Card(CardNumber.THREE, CardSuit.SPADE)) // 8
            player1.addCard(Card(CardNumber.TEN, CardSuit.CLUB))
            player1.addCard(Card(CardNumber.NINE, CardSuit.CLUB))
            player1.addCard(Card(CardNumber.TEN, CardSuit.HEART))
            val gameEvaluator = GameEvaluator()
            val gameResult = gameEvaluator.evaluate(dealer, players)

            Then("딜러는 베팅 금액을 얻고 플레이어는 베팅 금액을 잃는다") {
                val dealerResult = gameResult.getResultForPlayer(dealer.name)
                val player1Result = gameResult.getResultForPlayer(player1.name)

                dealerResult!!.formattedEarnings shouldBe "10000"
                player1Result!!.formattedEarnings shouldBe "-10000"
            }
        }
    }

    Given("두명의 참가자가 게임을 시작하고") {
        val dealer = Dealer()
        val player1 = Player("pobi", 10000)
        val player2 = Player("crong", 20000)
        val gameEvaluator = GameEvaluator()
        When("딜러가 버스트일 때") {
            dealer.addCard(Card(CardNumber.SIX, CardSuit.HEART))
            dealer.addCard(Card(CardNumber.KING, CardSuit.SPADE))
            dealer.addCard(Card(CardNumber.QUEEN, CardSuit.SPADE))

            player1.addCard(Card(CardNumber.TWO, CardSuit.CLUB))
            player1.addCard(Card(CardNumber.QUEEN, CardSuit.DIAMOND))
            player1.addCard(Card(CardNumber.JACK, CardSuit.DIAMOND))

            player2.addCard(Card(CardNumber.ACE, CardSuit.CLUB))
            player2.addCard(Card(CardNumber.QUEEN, CardSuit.DIAMOND))

            val matchResult = gameEvaluator.evaluate(dealer, listOf(player1, player2))
            Then("모든 참가자는 베팅 금액을 유지한다.") {
                matchResult.getMatchResult[player1.name] shouldBe Result(10000)
                matchResult.getMatchResult[player2.name] shouldBe Result(20000)
            }
        }

        When("참가자와 딜러 둘 다 블랙잭 일 경우") {
            dealer.addCard(Card(CardNumber.ACE, CardSuit.HEART))
            dealer.addCard(Card(CardNumber.KING, CardSuit.SPADE))

            player1.addCard(Card(CardNumber.ACE, CardSuit.CLUB))
            player1.addCard(Card(CardNumber.QUEEN, CardSuit.DIAMOND))

            player2.addCard(Card(CardNumber.ACE, CardSuit.CLUB))
            player2.addCard(Card(CardNumber.QUEEN, CardSuit.DIAMOND))
            Then("참가자는 모두 베팅 금액을 유지한다.") {
                val matchResult = gameEvaluator.evaluate(dealer, listOf(player1, player2))
                matchResult.getMatchResult[player1.name] shouldBe Result(10000)
                matchResult.getMatchResult[player2.name] shouldBe Result(20000)
            }
        }
    }
})
