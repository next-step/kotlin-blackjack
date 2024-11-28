package blackjack.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
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

        context("플레이어들의 손패를 설명할 때") {
            val players =
                Players(
                    listOf(
                        Player("pobi").apply {
                            addCard(Card(Suit.HEARTS, Rank.ACE))
                            addCard(Card(Suit.SPADES, Rank.KING))
                        },
                        Player("jason").apply {
                            addCard(Card(Suit.CLUBS, Rank.TWO))
                            addCard(Card(Suit.DIAMONDS, Rank.QUEEN))
                        },
                    ),
                )

            it("모든 플레이어의 이름과 손패 정보를 제공해야 한다") {
                players.describeHands() shouldContainExactly
                    listOf(
                        PlayerHand("pobi", "A하트, K스페이드"),
                        PlayerHand("jason", "2클로버, Q다이아몬드"),
                    )
            }
        }
    }
})
