package blackjack.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayersTest : DescribeSpec({
    describe("Players 클래스는") {

        context("플레이어들에게 초기 카드를 분배할 때") {
            val deck = Deck()
            val players =
                Players(
                    listOf(
                        Player("pobi"),
                        Player("jason"),
                    ),
                )

            it("모든 플레이어는 2장의 카드를 가져야 한다") {
                players.initializeHands(deck)

                players.participants.forEach { player ->
                    player.hand.cards.size shouldBe 2
                }
            }
        }
    }
})
