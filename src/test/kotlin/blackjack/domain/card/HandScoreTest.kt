package blackjack.domain.card

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HandScoreTest : DescribeSpec({
    describe("점수 계산") {
        context("Ace를 1점으로 계산했을 때 합이 11이하라면") {
            forAll(
                row(
                    Hand(
                        mutableListOf(
                            Card(Suit.SPADE, Rank.TWO), Card(Suit.CLUB, Rank.ACE)
                        )
                    ), 13
                ),
                row(
                    Hand(
                        mutableListOf(
                            Card(Suit.SPADE, Rank.TWO),
                            Card(Suit.DIAMOND, Rank.THREE),
                            Card(Suit.DIAMOND, Rank.FIVE),
                            Card(Suit.CLUB, Rank.ACE)
                        )
                    ), 21
                ),
            ) { hand, expect ->
                it("Ace 1개를 11점으로 계산한다") {
                    HandScore.from(hand) shouldBe HandScore(expect)
                }
            }
        }

        context("Ace를 1점으로 계산했을 때 합이 11초과라면") {
            val hand = Hand(
                mutableListOf(
                    Card(Suit.SPADE, Rank.TEN), Card(Suit.SPADE, Rank.TEN), Card(Suit.CLUB, Rank.ACE)
                )
            )
            it("Ace를 1점으로 계산한다") {
                HandScore.from(hand) shouldBe HandScore(21)
            }
        }

        context("Ace가 없는 카드에서 합이 11이하라면") {
            val hand = Hand(
                mutableListOf(
                    Card(Suit.SPADE, Rank.TWO), Card(Suit.CLUB, Rank.THREE)
                )
            )
            it("모든 점수를 그대로 계산") {
                HandScore.from(hand) shouldBe HandScore(5)
            }
        }
    }

    describe("최대 점수 21을 넘었는지 여부 반환") {
        context("최대 점수 21을 넘었을 때") {
            val handScore = HandScore(22)
            it("true 반환") {
                handScore.isOverMaxScore shouldBe true
            }
        }

        context("최대 점수 21을 넘지 않았을 때") {
            val handScore = HandScore(21)
            it("true 반환") {
                handScore.isOverMaxScore shouldBe false
            }
        }
    }
})
