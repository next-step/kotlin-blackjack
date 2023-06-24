package blakjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe

class DealerSpec : DescribeSpec({
    describe("카드 한장 뽑기 검증") {
        it("무작위로 카드 한장을 뽑을 수 있다.") {
            val dealer = Dealer()
            val card = dealer.drawOneCard()

            card shouldBeIn Card.values().toList()
        }

        context("이미 52장의 카드를 뽑았다면") {
            val dealer = Dealer()
            repeat(Card.values().size) {
                dealer.drawOneCard()
            }

            it("더 이상 카드를 뽑을 수 없다.") {
                shouldThrow<IllegalArgumentException> {
                    dealer.drawOneCard()
                }
            }
        }
    }

    describe("카드 두장 뽑기 검증") {
        it("무작위로 카드 두장을 뽑을 수 있다.") {
            val dealer = Dealer()
            val cards = dealer.drawTwoCards()

            cards.size shouldBe 2
        }

        context("이미 51장의 카드를 뽑았다면") {
            val dealer = Dealer()
            repeat(Card.values().size - 1) {
                dealer.drawOneCard()
            }

            it("더 이상 카드를 뽑을 수 없다.") {
                shouldThrow<IllegalArgumentException> {
                    dealer.drawTwoCards()
                }
            }
        }
    }
})
