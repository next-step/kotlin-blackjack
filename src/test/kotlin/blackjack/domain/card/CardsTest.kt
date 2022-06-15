package blackjack.domain.card

import blackjack.domain.score.Score
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CardsTest : DescribeSpec({

    describe("constructor") {
        context("카드 목록이 주어지면") {
            it("카드들을 생성할 수 있다") {
                val cards = cards {
                    card { "다이아몬드" to 4 }
                    card { "스페이드" to "A" }
                }

                cards shouldNotBe null
            }
        }
    }

    describe("add") {
        it("카드를 추가할 수 있다") {
            val cards = cards {
                card { "다이아몬드" to 4 }
                card { "스페이드" to "A" }
            }

            cards.add(card { "하트" to 7 })
            cards.add(card { "하트" to "Q" })

            cards.cards shouldContainExactly (
                listOf(
                    card { "다이아몬드" to 4 },
                    card { "스페이드" to "A" },
                    card { "하트" to 7 },
                    card { "하트" to "Q" }
                )
                )
        }
    }

    describe("count") {
        context("Ace 카드가 아닌 카드들이 주어졌을 때 ") {
            it("카드들의 카드 숫자를 합산할 수 있다") {
                val cards = cards {
                    card { "다이아몬드" to 4 }
                    card { "스페이드" to "J" }
                    card { "하트" to 7 }
                    card { "하트" to "Q" }
                }

                cards.score() shouldBe Score(31)
            }
        }

        context("Ace 카드를 포함한 카드들이 주어졌을 때 ") {
            it("Ace 카드를 제외한 숫자가 11이 이상이면 1로 계산하여 더한다") {
                val cards = cards {
                    card { "다이아몬드" to 4 }
                    card { "스페이드" to "A" }
                    card { "하트" to 7 }
                }

                cards.score() shouldBe Score(12)
            }

            it("Ace 카드를 제외한 숫자가 10이 이하이면 10으로 계산하여 더한다") {
                val cards = cards {
                    card { "스페이드" to "A" }
                    card { "하트" to "Q" }
                }

                cards.score() shouldBe Score(21)
            }
        }

        context("Ace 카드만 여러 장 주어졌을 때") {
            it("카드들의 카드 숫자를 합산할 수 있다") {
                val cards = cards {
                    card { "스페이드" to "A" }
                    card { "하트" to "A" }
                    card { "다이아몬드" to "A" }
                }

                cards.score() shouldBe Score(13)
            }
        }
    }
})
