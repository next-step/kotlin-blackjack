package blakjack.domain

import blakjack.domain.extension.cards
import blakjack.domain.extension.heart10
import blakjack.domain.extension.heart2
import blakjack.domain.extension.heart9
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe

class DealerSpec : DescribeSpec({
    describe("딜러 생성 검증") {
        context("이름이 '딜러'인 딜러를 생성하면") {
            val dealer = Dealer("딜러")

            it("이름은 '딜러'이다.") {
                dealer.name shouldBe "딜러"
            }
            it("카드 목록은 비어있다.") {
                dealer.cards shouldBe Cards.empty()
            }
        }
    }

    describe("카드 한장 뽑기 검증") {
        it("무작위로 카드 한장을 뽑을 수 있다.") {
            val dealer = Dealer()
            val card = dealer.drawOneCard()

            card.suit shouldBeIn CardSuit.values().toList()
            card.rank shouldBeIn CardRank.values().toList()
        }

        context("이미 52장의 카드를 뽑았다면") {
            val dealer = Dealer()
            repeat(52) {
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
            repeat(51) {
                dealer.drawOneCard()
            }

            it("더 이상 카드를 뽑을 수 없다.") {
                shouldThrow<IllegalArgumentException> {
                    dealer.drawTwoCards()
                }
            }
        }
    }

    describe("17점 이상 여부 검증") {
        context("점수가 17점 이상이면") {
            val dealer = Dealer().also { it.add(cards(heart10, heart9)) }

            it("17점 이상 여부는 '참'이다.") {
                dealer.isOver17 shouldBe true
            }
        }

        context("점수가 17점 미만이면") {
            val dealer = Dealer().also { it.add(cards(heart10, heart2)) }

            it("17점 이상 여부는 '거짓'이다.") {
                dealer.isOver17 shouldBe false
            }
        }
    }
})
