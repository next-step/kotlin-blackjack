package blakjack.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class GameSpec : DescribeSpec({
    describe("초기 카드 뽑기 검증") {
        context("초기 카드 뽑기가 실행되면") {
            val dealer = Dealer()
            val player = Player(name = "플레이어")
            val game = Game(dealer = dealer, players = listOf(player))

            game.initialDraw()

            it("플레이어는 카드 2장을 갖는다.") {
                player.cards.size shouldBe 2
            }
            it("딜러는 카드 2장을 갖는다.") {
                dealer.cards.size shouldBe 2
            }
        }
    }

    describe("플레이어 HIT(카드 추가) 검증") {
        context("플레이어에 카드 1장을 추가하면") {
            val player = Player("홍길동")
            val game = Game(players = listOf(player))

            it("카드 목록에 카드 1장이 추가된다.") {
                game.hit(player)

                player.cards.size shouldBe 1
            }
        }
    }
})
