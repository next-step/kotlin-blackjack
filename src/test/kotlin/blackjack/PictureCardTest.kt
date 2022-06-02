package blackjack

import balckjack.Ace
import balckjack.CardPattern
import balckjack.DoubleCount
import balckjack.Jack
import balckjack.King
import balckjack.Queen
import balckjack.SingleCount
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PictureCardTest : DescribeSpec({

    describe("constructor") {
        context("카드 문양과 카드 그림이 주어졌을 때") {
            it("그림 카드를 생성한다") {
                listOf(
                    King(CardPattern.HEART),
                    Queen(CardPattern.CLOVER),
                    Jack(CardPattern.SPADE),
                    Ace(CardPattern.DIAMOND)
                ).forAll { it shouldNotBe null }
            }
        }
    }

    describe("count") {
        context("카드 그림이 KING, QUEEN, JACK 인 카드가 주어졌을 때") {
            it("10을 반환한다") {
                listOf(
                    King(CardPattern.HEART),
                    Queen(CardPattern.CLOVER),
                    Jack(CardPattern.SPADE),
                ).forAll {
                    it.count() shouldBe SingleCount(10)
                }
            }
        }

        context("카드 그림이 ACE 인 카드가 주어졌을 때") {
            it("1 또는 11을 반환한다") {
                listOf(
                    Ace(CardPattern.HEART),
                    Ace(CardPattern.CLOVER),
                    Ace(CardPattern.DIAMOND),
                ).forAll {
                    it.count() shouldBe DoubleCount(1, 11)
                }
            }
        }
    }
})
