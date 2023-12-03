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
        context("테이블의 플레이어와 딜러") {
            val players = players(player("currentPlayer", Action.HIT), player())
            val dealer = Dealer()
            val table = table(dealer = dealer, players = players)
            val dealEnd = DealEnd(table)

            val result = dealEnd.deal()

            it("결과의 플레이어는 테이블의 플레이어") {
                result.players.value.forEachIndexed { index, player ->
                    player shouldBe players.value[index]
                }
            }

            it("결과의 딜러는 테이블의 딜러") {
                result.dealer shouldBe dealer
            }
        }
    }

    describe("nextDistributor") {
        context("dealEnd의 다음 배분자 호출") {
            val players = players(player("currentPlayer", Action.HIT), player())
            val dealer = Dealer()
            val table = table(dealer = dealer, players = players)
            val dealEnd = DealEnd(table)
            it("호출 실패") {
                shouldThrowExactly<IllegalArgumentException> {
                    dealEnd.nextDistributor()
                }
            }
        }
    }
})
