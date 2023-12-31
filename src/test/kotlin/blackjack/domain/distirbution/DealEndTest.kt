package blackjack.domain.distirbution

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.mock.player
import blackjack.mock.players
import blackjack.mock.table
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealEndTest : DescribeSpec({
    describe("deal") {
        context("DealEnd 의 카드 배분을 하면") {
            val players = players(player("currentPlayer", Action.HIT), player())
            val dealer = Dealer()
            val table = table(dealer = dealer, players = players)
            val dealEnd = DealEnd(table)

            val result = dealEnd.deal()

            it("결과에 반환된 플레이어들은 테이블의 모든 플레이어다") {
                result.players.value.forEachIndexed { index, player ->
                    player shouldBe players.value[index]
                }
            }

            it("결과의 반환된 딜러는 테이블의 딜러다") {
                result.dealer shouldBe dealer
            }
        }
    }

    describe("nextDistributor") {
        context("DealEnd의 다음 배분자 호출하면") {
            val players = players(player("currentPlayer", Action.HIT), player())
            val dealer = Dealer()
            val table = table(dealer = dealer, players = players)
            val dealEnd = DealEnd(table)
            it("다음 배분자가 없어 호출에 실패한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    dealEnd.nextDistributor()
                }
            }
        }
    }
})
