package blackjack.domain.blackjack

import blackjack.domain.card.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus
import blackjack.domain.player.Players
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
                    Deck.default(),
                    Players(listOf(Player("yohan"), Player("pang")))
                )

                blackJack shouldNotBe null
            }

            it("게임이 시작되면 각 플레이어에게 2장의 카드를 나누어준다") {
                val players = listOf(Player("yohan"), Player("pang"))
                BlackJack(players = Players(players))

                players.forAll { it.cards.size shouldBe 2 }
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

    describe("handOut") {
        context("카드를 추가할 수 있는 참가자가 주어지면") {
            it("카드를 지급한다") {
                val yohan = Player(name = "yohan")
                val blackJack = BlackJack(
                    players = Players(listOf(yohan))
                )

                blackJack.giveCard(yohan)

                yohan.cards.size shouldBe 3
            }
        }

        context("카드를 추가할 수 있는 참가자가 아니라면") {
            it("IllegalArgumentException 이 발생한다") {
                val target = Player("yohan")
                val blackJack = BlackJack(players = Players(listOf(target, Player("pang"))))
                target.changeStatus(PlayerStatus.STAY)

                shouldThrow<IllegalStateException> { blackJack.giveCard(target) }
            }
        }
    }

    describe("isEnd") {
        context("모든 참가자가 카드를 추가할 수 없으면") {
            it("게임이 종료된다") {
                val yohan = Player("yohan")
                val pang = Player("pang")
                val blackJack = BlackJack(players = Players(listOf(yohan, pang)))
                yohan.changeStatus(PlayerStatus.STAY)
                pang.changeStatus(PlayerStatus.STAY)

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
        context("참가자가 모두 카드 받을 수 없는 상태라면") {
            it("결과를 확인할 수 있다") {
                val yohan = Player("yohan")
                val pang = Player("pang")
                val blackJack = BlackJack(players = Players(listOf(yohan, pang)))
                yohan.changeStatus(PlayerStatus.STAY)
                pang.changeStatus(PlayerStatus.STAY)

                blackJack.result() shouldNotBe null
            }
        }

        context("참가자가 모두 카드 받을 수 없는 상태가 아니라면") {
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
