package blackjack.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class RankTest : DescribeSpec({
    describe("카드의 Rank는") {

        context("ACE는") {
            it("A로 표시된다") {
                Rank.ACE.displayName shouldBe "A"
            }
        }

        context("숫자 카드와 그림 카드는") {
            it("각 카드에 고유한 점수와 이름을 제공한다") {
                forAll(
                    row(Rank.TWO, 2, "2"),
                    row(Rank.THREE, 3, "3"),
                    row(Rank.FOUR, 4, "4"),
                    row(Rank.FIVE, 5, "5"),
                    row(Rank.SIX, 6, "6"),
                    row(Rank.SEVEN, 7, "7"),
                    row(Rank.EIGHT, 8, "8"),
                    row(Rank.NINE, 9, "9"),
                    row(Rank.TEN, 10, "10"),
                    row(Rank.JACK, 10, "J"),
                    row(Rank.QUEEN, 10, "Q"),
                    row(Rank.KING, 10, "K"),
                ) { rank, expectedScore, expectedDisplayName ->
                    rank.value shouldBe expectedScore
                    rank.displayName shouldBe expectedDisplayName
                }
            }
        }
    }
    describe("Rank.entries 리스트는") {
        it("모든 Rank를 포함해야 한다") {
            Rank.entries shouldContainExactly
                listOf(
                    Rank.ACE,
                    Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX,
                    Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN,
                    Rank.JACK, Rank.QUEEN, Rank.KING,
                )
        }

        it("올바른 개수를 가져야 한다") {
            Rank.entries.size shouldBe 13
        }
    }
})
