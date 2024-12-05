package blackjack.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class HandTest : DescribeSpec({
    describe("플레이어의 손패는") {
        val hand = Hand()

        it("초기 상태에서 카드 리스트는 비어 있어야 한다") {
            hand.cards.shouldBeEmpty()
        }

        it("카드를 추가할 수 있어야 한다") {
            val card = Card(Suit.HEARTS, Rank.ACE)
            hand.addCard(card)
            hand.cards.size shouldBe 1
            hand.cards[0] shouldBe card
        }
    }
    describe("플레이어 손패는") {
        context("점수 계산을 요청한 경우") {
            val hand =
                Hand().apply {
                    addCard(Card(Suit.HEARTS, Rank.ACE))
                    addCard(Card(Suit.SPADES, Rank.KING))
                }

            it("현재 점수를 계산한다") {
                hand.calculateScore() shouldBe 21
            }
        }

        context("점수가 21점을 초과한 경우") {
            val hand =
                Hand().apply {
                    addCard(Card(Suit.SPADES, Rank.KING))
                    addCard(Card(Suit.DIAMONDS, Rank.KING))
                    addCard(Card(Suit.CLUBS, Rank.TWO))
                }

            it("버스트 상태를 반환한다") {
                hand.isBusted() shouldBe true
            }
        }
        context("처음 두 장이 블랙잭 조건을 만족하는지 확인할 때") {
            forAll(
                row(
                    listOf(
                        Card(Suit.HEARTS, Rank.ACE),
                        Card(Suit.SPADES, Rank.KING),
                    ),
                    true,
                    "ACE와 K가 있는 경우 블랙잭",
                ),
                row(
                    listOf(
                        Card(Suit.CLUBS, Rank.ACE),
                        Card(Suit.DIAMONDS, Rank.QUEEN),
                    ),
                    true,
                    "ACE와 Q가 있는 경우 블랙잭",
                ),
                row(
                    listOf(
                        Card(Suit.HEARTS, Rank.ACE),
                        Card(Suit.SPADES, Rank.TEN),
                    ),
                    true,
                    "ACE와 10이 있는 경우 블랙잭",
                ),
                row(
                    listOf(
                        Card(Suit.HEARTS, Rank.ACE),
                        Card(Suit.SPADES, Rank.EIGHT),
                    ),
                    false,
                    "ACE와 8이 있는 경우 블랙잭이 아님",
                ),
                row(
                    listOf(
                        Card(Suit.HEARTS, Rank.NINE),
                        Card(Suit.SPADES, Rank.TEN),
                    ),
                    false,
                    "9와 10이 있는 경우 블랙잭이 아님",
                ),
                row(
                    listOf(
                        Card(Suit.HEARTS, Rank.ACE),
                        Card(Suit.SPADES, Rank.TEN),
                        Card(Suit.CLUBS, Rank.TWO),
                    ),
                    false,
                    "ACE와 10, 추가 카드가 있는 경우 블랙잭이 아님",
                ),
            ) { cards, expectedResult, description ->
                val hand =
                    Hand().apply {
                        cards.forEach { addCard(it) }
                    }

                it(description) {
                    hand.isBlackjack() shouldBe expectedResult
                }
            }
        }
    }
})
