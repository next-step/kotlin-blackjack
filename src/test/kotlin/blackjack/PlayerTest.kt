package blackjack

import blackjack.domain.AceCard
import blackjack.domain.Player
import blackjack.domain.Symbol
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : DescribeSpec({

    describe("cardCount") {
        context("플레이어가 가지고 있는 카드의 수가 2장이면") {
            it("2 를 리턴한다.") {
                val player = Player("name")
                player.addCard(AceCard(Symbol.Heart))
                player.addCard(AceCard(Symbol.Diamond))
                player.cardCount() shouldBe 2
            }
        }
    }
})