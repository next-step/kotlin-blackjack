package blakjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeIn

class CardDeckSpec : DescribeSpec({
    describe("카드 뽑기 검증") {
        context("카드 덱이 있을 때") {
            val cardDeck = CardDeck.create()

            it("카드를 랜덤으로 한장 뽑을 수 있다.") {
                val card = cardDeck.getCardRandomly()

                card.rank shouldBeIn CardRank.values().toList()
                card.suit shouldBeIn CardSuit.values().toList()
            }
        }

        context("모든 카드(52장)를 뽑았을 때") {
            val cardDeck = CardDeck.create()
            repeat(52) {
                cardDeck.getCardRandomly()
            }

            it("더 이상 카드를 뽑을 수 없다.") {
                shouldThrow<IllegalArgumentException> {
                    cardDeck.getCardRandomly()
                }
            }
        }
    }
})
