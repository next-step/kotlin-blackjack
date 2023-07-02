package blackjack.domain

import blackjack.domain.card.CardNumber
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

    CardNumber.NUMBER_RANGE.toList().forAll { number ->
        Given("CardNumber에 해당하는 숫자($number)가 주어졌다") {
            When("해당 숫자로 CardNumber를 만들면") {
                Then("적당한 Enum이 반환된다") {
                    CardNumber.of(number) shouldBe CardNumber.values()[number - 1]
                }
            }
        }
    }
})
