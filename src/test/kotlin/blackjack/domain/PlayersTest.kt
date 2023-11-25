package blackjack.domain

import blackjack.domain.state.Hit
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayersTest : BehaviorSpec({
    Given("플레이어의 이름들이 주어지면") {
        val names = listOf("pobi", "jason")
        When("Players는") {
            val players = Players.init(Dealer(FixedDeck()), names)
            Then("주어진 이름들을 갖는 Players를 생성한다.") {
                players.players[0].name shouldBe "pobi"
                players.players[1].name shouldBe "jason"
            }
        }
    }

    Given("플레이어가 생성되고 나면") {
        val dealer = Dealer(FixedDeck())
        val players = Players.init(dealer, listOf("pobi", "jason"))
        When("initCard를 통해") {
            players.initCard()
            Then("각 Player들은 카드 2장을 새로 갖는다.") {
                players.players[0].state.cards().cards[0] shouldBe Card(CardSuit.HEART, CardNumber.TWO)
                players.players[0].state.cards().cards[1] shouldBe Card(CardSuit.SPADE, CardNumber.EIGHT)
            }
        }
    }

    Given("게임이 모두 종료되고 나서") {
        val dealer = Dealer(FixedDeck(), Hit(Hand(mutableListOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)))))
        val player1 = Player("player1", Hit(Hand(mutableListOf(Card(CardSuit.DIAMOND, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))))
        val player2 = Player("player2", Hit(Hand(mutableListOf(Card(CardSuit.DIAMOND, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)))))
        val player3 = Player("player3", Hit(Hand(mutableListOf(Card(CardSuit.DIAMOND, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.ACE)))))
        val players = Players(dealer, listOf(player1, player2, player3))
        When("딜러의 결과를 가져오면") {
            val dealerResult = players.getDealerResult()
            Then("승무패가 각각 몇개가 나왔는지 반환한다.") {
                dealerResult[GameResult.WIN] shouldBe 1
                dealerResult[GameResult.DRAW] shouldBe 1
                dealerResult[GameResult.LOSE] shouldBe 1
            }
        }
    }

    Given("게임이 모두 종료된 후") {
        val dealer = Dealer(FixedDeck(), Hit(Hand(mutableListOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)))))
        val player1 = Player("player1", Hit(Hand(mutableListOf(Card(CardSuit.DIAMOND, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))))
        val player2 = Player("player2", Hit(Hand(mutableListOf(Card(CardSuit.DIAMOND, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)))))
        val player3 = Player("player3", Hit(Hand(mutableListOf(Card(CardSuit.DIAMOND, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.ACE)))))
        val players = Players(dealer, listOf(player1, player2, player3))
        When("플레이어를 결과를 가져오면") {
            val playerResult = players.getPlayersResult()
            Then("플레이어 별로 각각의 결과를 반환한다.") {
                playerResult["player1"] shouldBe GameResult.LOSE
                playerResult["player2"] shouldBe GameResult.DRAW
                playerResult["player3"] shouldBe GameResult.WIN
            }
        }
    }
})
