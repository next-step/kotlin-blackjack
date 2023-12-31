package blackjack.domain.distirbution

import blackjack.mock.table
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class DealInitialCardsTest : DescribeSpec({
    describe("deal") {
        context("DealInitialCards 카드 배분을 하면") {
            val table = table()
            val dealInitialCards = DealInitialCards(table)
            dealInitialCards.deal()

            it("플레이어마다 2장의 카드를 수령한다") {
                table.players.value.forEach { player ->
                    player.hand.cards.size shouldBe 2
                }
            }

            it("딜러는 2장의 카드를 수령한다") {
                table.dealer.hand.cards.size shouldBe 2
            }

            it("다음 카드 배분은 DealToPlayer이다") {
                dealInitialCards.nextDistributor.shouldBeTypeOf<DealToPlayer>()
            }
        }
    }
})
