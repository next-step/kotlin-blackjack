package blackjack.domain.blackjack

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus
import blackjack.domain.player.Players
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BlackJackTest : DescribeSpec({

    describe("constructor") {
        context("덱과 플레이어들이 주어지면") {
            it("블랙잭 게임을 생성한다") {
                val blackJack = BlackJack(
                    dealer = Dealer(),
                    players = Players(listOf(Player("yohan"), Player("pang")))
                )

                blackJack shouldNotBe null
            }

            it("게임이 시작되면 각 플레이어에게 2장의 카드를 나누어준다") {
                val players = listOf(Player("yohan"), Player("pang"))
                val dealer = Dealer()
                BlackJack(dealer = dealer, players = Players(players))

                assertSoftly {
                    players.forAll { it.cards.cards.size shouldBe 2 }
                    dealer.cards.cards.size shouldBe 2
                }
            }
        }
    }

    describe("hittablePlayers") {
        it("카드를 추가할 수 있는 참가자를 조회한다") {
            val yohan = Player(name = "yohan")
            val pang = Player("pang")
            val blackJack = BlackJack(players = Players(listOf(yohan, pang)))
            pang.changeStatus(PlayerStatus.STAY)

            blackJack.hittablePlayers shouldContainExactly listOf(yohan)
        }
    }

    describe("giveCard") {
        context("카드를 추가할 수 있는 참가자가 주어지면") {
            it("카드를 지급한다") {
                val yohan = Player(name = "yohan")
                val blackJack = BlackJack(
                    players = Players(listOf(yohan))
                )

                blackJack.giveCard(yohan)

                yohan.cards.cards.size shouldBe 3
            }
        }

        context("카드를 추가할 수 있는 참가자가 아니라면") {
            it("IllegalArgumentException 이 발생한다") {
                val target = Player("yohan")
                val blackJack = BlackJack(players = Players(listOf(target, Player("pang"))))
                target.changeStatus(PlayerStatus.STAY)

                shouldThrow<IllegalArgumentException> { blackJack.giveCard(target) }
            }
        }
    }

    describe("isEnd") {
        context("모든 참가자와 딜러가 카드를 추가할 수 없으면") {
            it("게임이 종료된다") {
                val dealer = Dealer()
                val yohan = Player("yohan")
                val pang = Player("pang")
                val blackJack = BlackJack(dealer = dealer, players = Players(listOf(yohan, pang)))

                yohan.changeStatus(PlayerStatus.STAY)
                pang.changeStatus(PlayerStatus.STAY)
                blackJack.playDealer()

                blackJack.isEnd shouldBe true
            }
        }

        context("카드를 추가할 수 있는 참가자가 있으면") {
            it("게임이 종료되지 않는다") {
                val yohan = Player("yohan")
                val pang = Player("pang")
                val blackJack = BlackJack(players = Players(listOf(yohan, pang)))
                yohan.changeStatus(PlayerStatus.STAY)

                blackJack.isEnd shouldBe false
            }
        }
    }

    describe("result") {
        context("참가자와 딜러의 플레이가 모두 종료되면") {
            it("결과를 확인할 수 있다") {
                val yohan = Player("yohan")
                val pang = Player("pang")
                val blackJack = BlackJack(players = Players(listOf(yohan, pang)))

                yohan.changeStatus(PlayerStatus.STAY)
                pang.changeStatus(PlayerStatus.STAY)
                blackJack.playDealer()

                blackJack.result() shouldNotBe null
            }
        }

        context("참가자나 딜러의 플레이가 모두 종료되지 않았다면") {
            it("IllegalStateException 이 발생한다") {
                val yohan = Player("yohan")
                val pang = Player("pang")
                val blackJack = BlackJack(players = Players(listOf(yohan, pang)))
                yohan.changeStatus(PlayerStatus.STAY)

                shouldThrow<IllegalStateException> { blackJack.result() }
            }
        }
    }
})
