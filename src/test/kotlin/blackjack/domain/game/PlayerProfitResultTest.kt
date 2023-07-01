package blackjack.domain.game

import blackjack.domain.card.cards
import blackjack.domain.card.heartAce
import blackjack.domain.card.heartJack
import blackjack.domain.card.heartKing
import blackjack.domain.card.heartQueen
import blackjack.domain.card.heartSeven
import blackjack.domain.card.heartTwo
import blackjack.domain.card.spadeAce
import blackjack.domain.card.spadeFour
import blackjack.domain.card.spadeKing
import blackjack.domain.card.spadeQueen
import blackjack.domain.card.spadeTen
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player
import blackjack.domain.money.Money
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerProfitResultTest : BehaviorSpec({

    val betAmount = Money(1_000)

    Given("딜러가 bust 일 때") {
        val dealer = Dealer().apply {
            init(cards(heartJack(), heartTwo())) // j(10) + 2 = 12
            hit(heartQueen()) // 12 + q(10) = 22
        }

        When("플레이어가 BlackJack 이면") {
            val player = Player("test", betAmount).apply {
                init(cards(spadeAce(), spadeKing())) // a(11) + k(10) = 21
                stay()
            }

            Then("수익은 투자 금액의 1.5배이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * 1.5
            }
        }

        When("플레이어가 BlackJack이 아니면") {
            val player = Player("test", betAmount).apply {
                init(cards(spadeQueen(), spadeKing())) // q(10) + k(10) = 20
                stay()
            }

            Then("수익은 투자 금액과 동일하다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * 1.0
            }
        }
    }

    Given("플레이어가 BlackJack 일 떄") {
        val player = Player("test", betAmount).apply {
            init(cards(spadeAce(), spadeKing())) // a(11) + k(10) = 21
            stay()
        }

        When("딜러가 BlackJack 이면") {
            val dealer = Dealer().apply {
                init(cards(heartAce(), heartKing())) // a(11) + k(10) = 21
                stay()
            }

            Then("수익은 0이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe 0
            }
        }

        When("딜러가 BlackJack이 아니면") {
            val dealer = Dealer().apply {
                init(cards(heartQueen(), heartQueen())) // q(10) + k(10) = 20
                stay()
            }

            Then("수익은 투자 금액의 1.5배이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * 1.5
            }
        }
    }

    Given("플레이어가 stay 일 때") {
        val player = Player("test", betAmount).apply {
            init(cards(spadeQueen(), spadeKing())) // q(10) + k(10) = 20
            stay()
        }

        When("딜러가 BlackJack 이면") {
            val dealer = Dealer().apply {
                init(cards(heartAce(), heartQueen())) // a(11) + q(10) = 21
                stay()
            }

            Then("수익은 배팅 금액의 -1배이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * -1.0
            }
        }

        When("딜러보다 점수가 높으면") {
            val dealer = Dealer().apply {
                init(cards(heartSeven(), heartQueen())) // 7 + q(10) = 17
                stay()
            }

            Then("수익은 배팅 금액의 1배이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * 1.0
            }
        }

        When("딜러와 점수가 같다면") {
            val dealer = Dealer().apply {
                init(cards(heartKing(), heartQueen())) // k(10) + q(10) = 20
                stay()
            }

            Then("수익은 0이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe 0
            }
        }

        When("딜러보다 점수가 낮다면") {
            val dealer = Dealer().apply {
                init(cards(heartKing(), heartQueen())) // k(10) + q(10) = 20
                hit(heartAce()) // 20 + a(1) = 21
                stay()
            }
            Then("수익은 0이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * -1.0
            }
        }
    }

    Given("플레이어가 bust 일 때") {
        val player = Player("test", betAmount).apply {
            init(cards(heartJack(), heartQueen())) // j(10) + q(10) = 20
            hit(heartKing()) // 20 + k(10) = 30
        }
        val dealer = Dealer().apply {
            init(cards(spadeTen(), spadeFour())) // 10 + 4 = 14
            stay()
        }

        Then("수익은 투자 금액의 -1배이다") {
            PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * -1.0
        }
    }
})
