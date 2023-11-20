package blackjack.domain.card

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class HandTest : DescribeSpec({
    describe("카드 추가") {
        context("카드가 주어지면") {
            val hand = Hand()
            val card = Card(Suit.HEART, Rank.FOUR)

            hand.add(card)

            it("카드 목록에 추가") {
                hand.cards shouldBe listOf(card)
            }
        }

        context("다른 카드가 있던 경우") {
            val oldCard = Card(Suit.DIAMOND, Rank.ACE)
            val hand = Hand(mutableListOf(oldCard))
            val newCard = Card(Suit.HEART, Rank.FOUR)

            hand.add(newCard)

            it("카드 목록에 추가") {
                hand.cards shouldBe listOf(oldCard, newCard)
            }
        }
    }

    describe("rank 목록 반환") {
        context("해당 카드의 rank 요청") {
            val hand = Hand(
                mutableListOf(
                    Card(Suit.SPADE, Rank.FIVE), Card(Suit.DIAMOND, Rank.THREE), Card(Suit.CLUB, Rank.FIVE)
                )
            )
            it("해당 카드의 rank 반환") {
                hand.ranks shouldBe listOf(Rank.FIVE, Rank.THREE, Rank.FIVE)
            }
        }
    }

    describe("점수 계산") {
        context("카드 점수 조회") {
            val hand = Hand(
                mutableListOf(
                    Card(Suit.CLUB, Rank.ACE),
                    Card(Suit.CLUB, Rank.TEN),
                    Card(Suit.CLUB, Rank.JACK),
                )
            )
            it("카드 점수 반환") {
                hand.score shouldBe HandScore(21)
            }
        }
    }
})
