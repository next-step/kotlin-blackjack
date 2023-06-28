package blakjack.domain

import blakjack.domain.extension.cards
import blakjack.domain.extension.heart10
import blakjack.domain.extension.heart2
import blakjack.domain.extension.heart3
import blakjack.domain.extension.heart9
import blakjack.domain.extension.heartAce
import blakjack.domain.extension.spadeAce
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardsSpec : DescribeSpec({
    describe("카드 추가 검증") {
        context("빈 카드 목록에 카드 1장을 추가하면") {
            val cards = Cards.empty()
            val newCards = cards.add(heart2)

            it("1장의 카드 목록을 반환한다.") {
                newCards.size shouldBe 1
            }
            it("추가한 카드가 포함된 카드 목록을 반환한다.") {
                newCards.values shouldBe setOf(heart2)
            }
        }

        context("2장의 카드 목록에 카드 1장을 추가하면") {
            val cards = cards(heart2, heart3)
            val newCards = cards.add(heart10)

            it("3장의 카드 목록을 반환한다.") {
                newCards.size shouldBe 3
            }
            it("추가한 카드가 포함된 카드 목록을 반환한다.") {
                newCards.values shouldBe setOf(heart2, heart3, heart10)
            }
        }

        context("중복된 카드를 추가하면") {
            val cards = cards(heart2, heart3)
            val newCards = cards.add(heart2)

            it("추가되지 않는다. (기존 카드 목록을 반환한다.)") {
                newCards shouldBe cards
            }
        }
    }

    describe("카드 목록 추가 검증") {
        context("빈 카드 목록에 카드 목록 1장을 추가하면") {
            val cards = Cards.empty()
            val newCards = cards.add(cards(heart2))

            it("1장의 카드 목록을 반환한다.") {
                newCards.size shouldBe 1
            }
            it("추가한 카드가 포함된 카드 목록을 반환한다.") {
                newCards.values shouldBe setOf(heart2)
            }
        }

        context("2장의 카드 목록에 카드 목록 1장을 추가하면") {
            val cards = cards(heart2, heart3)
            val newCards = cards.add(cards(heart10))

            it("3장의 카드 목록을 반환한다.") {
                newCards.size shouldBe 3
            }
            it("추가한 카드가 포함된 카드 목록을 반환한다.") {
                newCards.values shouldBe setOf(heart2, heart3, heart10)
            }
        }

        context("중복이 포함된 카드 목록을 추가하면") {
            val cards = cards(heart2, heart3)
            val newCards = cards.add(cards(heart2, heart10))

            it("중복된 카드만 추가되지 않는다.") {
                newCards.values shouldBe setOf(heart2, heart3, heart10)
            }
        }
    }

    describe("카드 목록 점수 합산 검증") {
        withData(
            nameFn = { (cards, sum) -> "카드 목록에 카드 ${cards.size}장(${cards.values.joinToString(",")})이 있을 때 점수는 ${sum}이다." },
            ts = listOf(
                cards(heart2, heart3) to 5,
                cards(heartAce, heart10) to 21,
                cards(heartAce, heart10, spadeAce) to 12,
                cards(heartAce, heart9, spadeAce) to 21,
                cards(heartAce, heart10, heart2) to 13,
            )
        ) { (cards, sum) ->
            cards.score() shouldBe sum
        }
    }
})
