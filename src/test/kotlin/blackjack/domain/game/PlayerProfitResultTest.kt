package blackjack.domain.game

import blackjack.domain.card.CardFixture
import blackjack.domain.card.cards
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player
import blackjack.domain.money.Money
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerProfitResultTest : BehaviorSpec({

    val betAmount = Money(1_000)

    Given("딜러가 bust 일 때") {
        val dealer = Dealer().apply {
            init(cards(CardFixture.heartJack, CardFixture.heartTwo)) // j(10) + 2 = 12
            hit(CardFixture.heartQueen) // 12 + q(10) = 22
        }

        When("플레이어가 BlackJack 이면") {
            val player = Player("test", betAmount).apply {
                init(cards(CardFixture.spadeAce, CardFixture.spadeKing)) // a(11) + k(10) = 21
                stay()
            }

            Then("수익은 투자 금액의 1.5배이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * 1.5
            }
        }

        When("플레이어가 BlackJack이 아니면") {
            val player = Player("test", betAmount).apply {
                init(cards(CardFixture.spadeQueen, CardFixture.spadeKing)) // q(10) + k(10) = 20
                stay()
            }

            Then("수익은 투자 금액과 동일하다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * 1.0
            }
        }
    }

    Given("플레이어가 BlackJack 일 떄") {
        val player = Player("test", betAmount).apply {
            init(cards(CardFixture.spadeAce, CardFixture.spadeKing)) // a(11) + k(10) = 21
            stay()
        }

        When("딜러가 BlackJack 이면") {
            val dealer = Dealer().apply {
                init(cards(CardFixture.heartAce, CardFixture.heartKing)) // a(11) + k(10) = 21
                stay()
            }

            Then("수익은 0이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe 0
            }
        }

        When("딜러가 BlackJack이 아니면") {
            val dealer = Dealer().apply {
                init(cards(CardFixture.heartQueen, CardFixture.heartKing)) // q(10) + k(10) = 20
                stay()
            }

            Then("수익은 투자 금액의 1.5배이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * 1.5
            }
        }
    }

    Given("플레이어가 stay 일 때") {
        val player = Player("test", betAmount).apply {
            init(cards(CardFixture.spadeQueen, CardFixture.spadeKing)) // q(10) + k(10) = 20
            stay()
        }

        When("딜러가 BlackJack 이면") {
            val dealer = Dealer().apply {
                init(cards(CardFixture.heartAce, CardFixture.heartQueen)) // a(11) + q(10) = 21
                stay()
            }

            Then("수익은 배팅 금액의 -1배이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * -1.0
            }
        }

        When("딜러보다 점수가 높으면") {
            val dealer = Dealer().apply {
                init(cards(CardFixture.heartSeven, CardFixture.heartQueen)) // 7 + q(10) = 17
                stay()
            }

            Then("수익은 배팅 금액의 1배이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * 1.0
            }
        }

        When("딜러와 점수가 같다면") {
            val dealer = Dealer().apply {
                init(cards(CardFixture.heartKing, CardFixture.heartQueen)) // k(10) + q(10) = 20
                stay()
            }

            Then("수익은 0이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe 0
            }
        }

        When("딜러보다 점수가 낮다면") {
            val dealer = Dealer().apply {
                init(cards(CardFixture.heartKing, CardFixture.heartQueen)) // k(10) + q(10) = 20
                hit(CardFixture.heartAce) // 20 + a(1) = 21
                stay()
            }
            Then("수익은 0이다") {
                PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * -1.0
            }
        }
    }

    Given("플레이어가 bust 일 때") {
        val player = Player("test", betAmount).apply {
            init(cards(CardFixture.heartJack, CardFixture.heartQueen)) // j(10) + q(10) = 20
            hit(CardFixture.heartKing) // 20 + k(10) = 30
        }
        val dealer = Dealer().apply {
            init(cards(CardFixture.spadeTen, CardFixture.spadeFour)) // 10 + 4 = 14
            stay()
        }

        Then("수익은 투자 금액의 -1배이다") {
            PlayerProfitResult.create(player, dealer).profit shouldBe betAmount * -1.0
        }
    }

    Given("플레이어가 finished 일 때") {
        val player = Player("test", betAmount).apply {
            init(cards(CardFixture.spadeQueen, CardFixture.spadeKing))
            stay() // finished 상태
        }

        When("딜러가 finished 상태가 아니면") {
            val dealer = Dealer().apply {
                init(cards(CardFixture.heartJack, CardFixture.heartQueen))
            } // hit 상태

            Then("RuntimeException 예외 처리를 한다") {
                shouldThrow<RuntimeException> {
                    PlayerProfitResult.create(player, dealer)
                }
            }
        }
    }

    Given("딜러가 finished 일 떄") {
        val dealer = Dealer().apply {
            init(cards(CardFixture.heartJack, CardFixture.heartQueen))
            stay() // finished 상태
        }

        When("플레이어가 finished 상태가 아니면") {
            val player = Player("test", betAmount).apply {
                init(cards(CardFixture.spadeQueen, CardFixture.spadeKing))
            } // hit 상태

            Then("RuntimeException 예외 처리를 한다") {
                shouldThrow<RuntimeException> {
                    PlayerProfitResult.create(player, dealer)
                }
            }
        }
    }
})
