package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardNumberTest : BehaviorSpec({

    Given("카드의 최소 숫자보다 작은 숫자가 주어졌다") {
        val number = CardNumber.MIN_CARD_NUMBER - 1
        When("해당 숫자로 CardNumber를 만들면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { CardNumber.of(number) }
            }
        }
    }

    Given("카드의 최대 숫자보다 큰 숫자가 주어졌다") {
        val number = CardNumber.MAX_CARD_NUMBER + 1
        When("해당 숫자로 CardNumber를 만들면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { CardNumber.of(number) }
            }
        }
    }

    Given("Ace에 해당하지 않는 숫자가 주어졌다") {
        val number = AceCardNumber.NUMBER_RANGE.last + 1
        When("해당 숫자로 CardNumber를 만들면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { AceCardNumber(number) }
            }
        }
    }

    AceCardNumber.NUMBER_RANGE.toList().forAll { number ->
        Given("Ace에 해당하는 숫자($number)가 주어졌다") {
            When("해당 숫자로 CardNumber를 만들면") {
                Then("AceCardNumber가 생성된다") {
                    CardNumber.of(number) shouldBe AceCardNumber(number)
                }
            }
        }
    }

    Given("숫자 카드에 해당하지 않는 숫자가 주어졌다") {
        val number = NumberCardNumber.NUMBER_RANGE.last + 1
        When("해당 숫자로 CardNumber를 만들면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { NumberCardNumber(number) }
            }
        }
    }

    NumberCardNumber.NUMBER_RANGE.toList().forAll { number ->
        Given("숫자 카드에 해당하는 숫자($number)가 주어졌다") {
            When("해당 숫자로 CardNumber를 만들면") {
                Then("NumberCardNumber가 생성된다") {
                    CardNumber.of(number) shouldBe NumberCardNumber(number)
                }
            }
        }
    }

    Given("J,Q,K에 해당하지 않는 숫자가 주어졌다") {
        val number = JackQueenKingCardNumber.NUMBER_RANGE.last + 1
        When("해당 숫자로 CardNumber를 만들면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { JackQueenKingCardNumber(number) }
            }
        }
    }

    JackQueenKingCardNumber.NUMBER_RANGE.toList().forAll { number ->
        Given("J,Q,K에 해당하는 숫자($number)가 주어졌다") {
            When("해당 숫자로 CardNumber를 만들면") {
                Then("JackQueenKingCardNumber가 생성된다") {
                    CardNumber.of(number) shouldBe JackQueenKingCardNumber(number)
                }
            }
        }
    }
})
