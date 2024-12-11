package blackjack.domain.player

import blackjack.domain.card.Hand
import blackjack.domain.card.Suit.DIAMOND
import blackjack.domain.card.Suit.HEART
import blackjack.domain.card.Suit.SPADE
import blackjack.domain.game.MatchResult.DRAW
import blackjack.domain.game.MatchResult.LOSE
import blackjack.domain.game.MatchResult.WIN
import blackjack.fixtures.createCard
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    Given("플레이어 핸드가 21점 미만인 경우") {
        table(
            headers("ranks"),
            row(listOf("2", "3", "4")),
            row(listOf("4", "5", "10")),
            row(listOf("K", "Q")),
            row(listOf("5", "5", "4", "3", "2")),
        ).forAll { ranks ->
            val player = Player("홍길동", Hand(ranks.map { createCard(it) }.toMutableList()))

            When("히트 가능 여부 질의하면") {
                Then("결과는 true 이다") {
                    player.canHit() shouldBe true
                }
            }
        }
    }

    Given("플레이어 핸드가 21점 이상인 경우") {
        table(
            headers("ranks"),
            row(listOf("J", "Q", "K")),
            row(listOf("A", "K")),
            row(listOf("Q", "10", "A")),
            row(listOf("10", "9", "2")),
            row(listOf("10", "10", "3")),
        ).forAll { ranks ->
            val player = Player("홍길동", Hand(ranks.map { createCard(it) }.toMutableList()))

            When("히트 가능 여부 질의하면") {
                Then("결과는 false 이다") {
                    player.canHit() shouldBe false
                }
            }
        }
    }

    Given("플레이어가 딜러보다 점수가 높은 경우") {
        val dealer = Dealer()
        val player1 = Player("유저1")
        val player2 = Player("유저2")

        dealer.hit(createCard("3"))

        player1.hit(createCard("4"))
        player2.hit(createCard("5"))

        When("게임 결과 비교하면") {
            Then("플레이어가 승리한다") {
                listOf(player1, player2).forAll { player -> player.matchHand(dealer) shouldBe WIN }
            }
        }
    }

    Given("플레이어가 딜러보다 점수가 낮은 경우") {
        val dealer = Dealer()
        val player1 = Player("유저1")
        val player2 = Player("유저2")

        dealer.hit(createCard("10"))

        player1.hit(createCard("4"))
        player2.hit(createCard("5"))

        When("게임 결과 비교하면") {
            Then("플레이어가 승리한다") {
                listOf(player1, player2).forAll { player -> player.matchHand(dealer) shouldBe LOSE }
            }
        }
    }

    Given("플레이어가 딜러보다 점수가 동일한 경우") {
        val dealer = Dealer()
        val player1 = Player("유저1")
        val player2 = Player("유저2")

        dealer.hit(createCard("10", SPADE))

        player1.hit(createCard("10", HEART))
        player2.hit(createCard("10", DIAMOND))

        When("게임 결과 비교하면") {
            Then("플레이어가 승리한다") {
                listOf(player1, player2).forAll { player -> player.matchHand(dealer) shouldBe DRAW }
            }
        }
    }

    Given("딜러가 버스트인 경우") {
        val dealer = Dealer()
        val player1 = Player("유저1")
        val player2 = Player("유저2")

        dealer.hit(createCard("K", SPADE))
        dealer.hit(createCard("Q", SPADE))
        dealer.hit(createCard("J", SPADE))

        player1.hit(createCard("7", HEART))
        player2.hit(createCard("8", DIAMOND))

        When("게임 결과 비교하면") {
            Then("모든 플레이어가 승리한다") {
                listOf(player1, player2).forAll { player -> player.matchHand(dealer) shouldBe WIN }
            }
        }

        When("게임 결과 비교하면 플레이어 핸드가 21점을 초과해도") {
            player1.hit(createCard("K", HEART))
            player1.hit(createCard("Q", HEART))
            player1.hit(createCard("J", HEART))

            player2.hit(createCard("K", DIAMOND))
            player2.hit(createCard("Q", DIAMOND))
            player2.hit(createCard("J", DIAMOND))

            Then("모든 플레이어가 승리한다") {
                listOf(player1, player2).forAll { player -> player.matchHand(dealer) shouldBe WIN }
            }
        }
    }
})
