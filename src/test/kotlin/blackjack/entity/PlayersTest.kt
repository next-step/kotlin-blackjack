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
                            receiveCard(Card(Suit.HEARTS, Rank.ACE))
                            receiveCard(Card(Suit.SPADES, Rank.KING))
                        },
                        Player("jason").apply {
                            receiveCard(Card(Suit.CLUBS, Rank.TWO))
                            receiveCard(Card(Suit.DIAMONDS, Rank.QUEEN))
                        },
                    ),
                )

            it("모든 플레이어의 이름과 손패 정보를 제공해야 한다") {
                val result = players.describeHands()
                result[0].playerName shouldBe "pobi"
                result[0].hand.cards shouldContainExactly
                    listOf(
                        Card(Suit.HEARTS, Rank.ACE),
                        Card(Suit.SPADES, Rank.KING),
                    )
                result[1].playerName shouldBe "jason"
                result[1].hand.cards shouldContainExactly
                    listOf(
                        Card(Suit.CLUBS, Rank.TWO),
                        Card(Suit.DIAMONDS, Rank.QUEEN),
                    )
            }
        }
    }
})
