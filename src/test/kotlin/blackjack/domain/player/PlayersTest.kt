package blackjack.domain.player

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.card.Deck
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlayersTest : DescribeSpec({

    describe("handOutTwoCard") {
        it("각 플레이어에게 카드를 두장 씩 나누어준다") {
            val players = listOf(Player("yohan"), Player("pang"))

            Players(players).addTwoCard(Deck.default())

            players.forAll { it.cards.size shouldBe 2 }
        }
    }

    describe("hittablePlayers") {
        it("카드를 추가할 수 있는 참가자를 조회한다") {
            val yohan = Player(name = "yohan")
            val pang = Player(name = "pang")
            val blackJack = BlackJack(players = Players(listOf(yohan, pang)))
            pang.changeStatus(PlayerStatus.STAY)

            blackJack.hittablePlayers shouldContainExactly listOf(yohan)
        }
    }

    describe("isEnd") {
        context("모든 참여자가 종료 상태이면") {
            it("true 를 반환한다") {
                val yohan = Player(name = "yohan", playerStatus = PlayerStatus.STAY)
                val pang = Player(name = "pang", playerStatus = PlayerStatus.STAY)

                Players(listOf(yohan, pang)).isEnd() shouldBe true
            }
        }

        context("모든 참여자가 종료 상태가 아니면") {
            it("false 를 반환한다") {
                val yohan = Player(name = "yohan", playerStatus = PlayerStatus.STAY)
                val pang = Player(name = "pang")

                Players(listOf(yohan, pang)).isEnd() shouldBe false
            }
        }
    }
})
