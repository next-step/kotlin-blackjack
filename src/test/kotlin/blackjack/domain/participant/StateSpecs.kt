package blackjack.domain.participant

import blackjack.domain.card.Point
import blackjack.domain.participant.Match.DRAW
import blackjack.domain.participant.Match.LOSE
import blackjack.domain.participant.Match.WIN
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class StateSpecs : DescribeSpec({

    describe("Hittable 상태는") {
        it("다른 상태와 비교할 수 없다") {
            listOf(Bust, BlackJack, Stay(Point(0))).forAll {
                shouldThrowExactly<IllegalStateException> {
                    Hittable.compare(Bust)
                }
            }
        }
    }

    describe("BlackJack 상태는") {
        it("BlackJack 상태와 비긴다") {
            BlackJack.compare(BlackJack) shouldBe DRAW
        }
        it("BlackJack이 아닌 모든 상태를 반드시 이긴다") {
            listOf(Bust, Stay(Point(0))).forAll {
                BlackJack.compare(it) shouldBe WIN
            }
        }
    }

    describe("Bust 상태는") {
        it("Bust 상태와 비긴다") {
            Bust.compare(Bust) shouldBe DRAW
        }
        it("Bust가 아닌 모든 상태에게 진다") {
            listOf(BlackJack, Stay(Point(0))).forAll {
                Bust.compare(it) shouldBe LOSE
            }
        }
    }

    describe("Stay 상태는") {
        val stay = Stay(Point(10))
        it("BlackJack 상태에겐 반드시 진다") {
            Bust.compare(BlackJack) shouldBe LOSE
        }
        it("점수가 높은 Stay 상태와 비교하면 진다") {
            stay.compare(Stay(Point(15))) shouldBe LOSE
        }
        it("점수가 같은 Stay 상태와 비교하면 비긴다") {
            stay.compare(Stay(Point(10))) shouldBe DRAW
        }
        it("점수가 낮은 Stay 상태와 비교하면 이긴다") {
            stay.compare(Stay(Point(9))) shouldBe WIN
        }
        it("Bust 상태에겐 반드시 이긴다") {
            stay.compare(Bust) shouldBe WIN
        }
    }
})
