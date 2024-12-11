package blackjack.domain.game

import blackjack.domain.card.Suit.DIAMOND
import blackjack.domain.card.Suit.HEART
import blackjack.domain.card.Suit.SPADE
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.fixtures.createCard
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameResultTest : BehaviorSpec({
    Given("플레이어가 이긴 경우") {
        val dealer = Dealer()
        val player1 = Player("유저1")
        val player2 = Player("유저2")

        dealer.hit(createCard("3", SPADE))
        player1.hit(createCard("4", HEART))
        player2.hit(createCard("5", DIAMOND))

        When("게임결과를 생성하면") {
            val actual = GameResult.from(dealer, listOf(player1, player2))

            Then("딜러의 패배 카운트가 증가한다") {
                actual.dealerGameResult.loseCount shouldBe 2
            }
        }
    }

    Given("플레이어가 진 경우") {
        val dealer = Dealer()
        val player1 = Player("유저1")
        val player2 = Player("유저2")

        dealer.hit(createCard("10", SPADE))
        player1.hit(createCard("4", HEART))
        player2.hit(createCard("5", DIAMOND))

        When("게임결과를 생성하면") {
            val actual = GameResult.from(dealer, listOf(player1, player2))

            Then("딜러의 승리 카운트가 증가한다") {
                actual.dealerGameResult.winCount shouldBe 2
            }
        }
    }

    Given("플레이어가 딜러와 무승부인 경우") {
        val dealer = Dealer()
        val player1 = Player("유저1")
        val player2 = Player("유저2")

        dealer.hit(createCard("10", SPADE))
        player1.hit(createCard("10", HEART))
        player2.hit(createCard("10", DIAMOND))

        When("게임결과를 생성하면") {
            val actual = GameResult.from(dealer, listOf(player1, player2))

            Then("딜러의 무승부 카운트가 증가한다") {
                actual.dealerGameResult.drawCount shouldBe 2
            }
        }
    }
})
