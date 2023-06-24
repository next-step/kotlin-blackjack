package blakjack.domain

import blakjack.domain.extension.cards
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardsSpec : DescribeSpec({
    describe("카드 추가 검증") {
        context("빈 카드 목록에 카드 1장을 추가하면") {
            val cards = Cards.empty()
            val newCards = cards.add(Card.SPADE_10)

            it("1장의 카드 목록을 반환한다.") {
                newCards.size shouldBe 1
            }
            it("추가한 카드가 포함된 카드 목록을 반환한다.") {
                newCards.values shouldBe setOf(Card.SPADE_10)
            }
        }

        context("2장의 카드 목록에 카드 1장을 추가하면") {
            val cards = cards(Card.CLOVER_2, Card.HEART_3)
            val newCards = cards.add(Card.CLOVER_3)

            it("3장의 카드 목록을 반환한다.") {
                newCards.size shouldBe 3
            }
            it("추가한 카드가 포함된 카드 목록을 반환한다.") {
                newCards.values shouldBe setOf(Card.CLOVER_2, Card.HEART_3, Card.CLOVER_3)
            }
        }

        context("중복된 카드를 추가하면") {
            val cards = cards(Card.CLOVER_2, Card.HEART_3)
            val newCards = cards.add(Card.CLOVER_2)

            it("추가되지 않는다. (기존 카드 목록을 반환한다.)") {
                newCards shouldBe cards
            }
        }
    }

    describe("카드 목록 추가 검증") {
        context("빈 카드 목록에 카드 목록 1장을 추가하면") {
            val cards = Cards.empty()
            val newCards = cards.add(cards(Card.SPADE_10))

            it("1장의 카드 목록을 반환한다.") {
                newCards.size shouldBe 1
            }
            it("추가한 카드가 포함된 카드 목록을 반환한다.") {
                newCards.values shouldBe setOf(Card.SPADE_10)
            }
        }

        context("2장의 카드 목록에 카드 목록 1장을 추가하면") {
            val cards = cards(Card.SPADE_10, Card.HEART_2)
            val newCards = cards.add(cards(Card.CLOVER_3))

            it("3장의 카드 목록을 반환한다.") {
                newCards.size shouldBe 3
            }
            it("추가한 카드가 포함된 카드 목록을 반환한다.") {
                newCards.values shouldBe setOf(Card.SPADE_10, Card.HEART_2, Card.CLOVER_3)
            }
        }

        context("중복이 포함된 카드 목록을 추가하면") {
            val cards = cards(Card.SPADE_10, Card.HEART_2)
            val newCards = cards.add(cards(Card.SPADE_10, Card.CLOVER_3))

            it("중복된 카드만 추가되지 않는다.") {
                newCards.values shouldBe setOf(Card.SPADE_10, Card.HEART_2, Card.CLOVER_3)
            }
        }
    }

    describe("카드 목록 점수 합산 검증") {
        withData(
            nameFn = { (cards, sum) -> "카드 목록에 카드 ${cards.size}장(${cards.values.joinToString(",")})이 있을 때 점수는 ${sum}이다." },
            ts = listOf(
                cards(Card.HEART_2, Card.CLOVER_3) to 5,
                cards(Card.HEART_ACE, Card.DIAMOND_KING) to 21,
                cards(Card.HEART_ACE, Card.DIAMOND_KING, Card.CLOVER_ACE) to 12,
                cards(Card.HEART_ACE, Card.DIAMOND_9, Card.SPADE_ACE) to 21,
                cards(Card.HEART_ACE, Card.HEART_10, Card.CLOVER_2) to 13,
            )
        ) { (cards, sum) ->
            cards.score() shouldBe sum
        }
    }
})
