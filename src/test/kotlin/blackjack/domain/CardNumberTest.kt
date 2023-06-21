package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardNumberTest : BehaviorSpec({

    given("카드의 최소 숫자보다 작은 숫자가 주어졌다") {
        val number = CardNumber.MIN_CARD_NUMBER - 1
        `when`("해당 숫자로 CardNumber를 만들면") {
            then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { CardNumber.of(number) }
            }
        }
    }

    given("카드의 최대 숫자보다 큰 숫자가 주어졌다") {
        val number = CardNumber.MAX_CARD_NUMBER + 1
        `when`("해당 숫자로 CardNumber를 만들면") {
            then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { CardNumber.of(number) }
            }
        }
    }

    AceCardNumber.NUMBER_RANGE.toList().forAll { number ->
        given("Ace에 해당하는 숫자($number)가 주어졌다") {
            `when`("해당 숫자로 CardNumber를 만들면") {
                then("AceCardNumber가 생성된다") {
                    CardNumber.of(number) shouldBe AceCardNumber(number)
                }
            }
        }
    }

    NumberCardNumber.NUMBER_RANGE.toList().forAll { number ->
        given("숫자에 해당하는 숫자($number)가 주어졌다") {
            `when`("해당 숫자로 CardNumber를 만들면") {
                then("NumberCardNumber가 생성된다") {
                    CardNumber.of(number) shouldBe NumberCardNumber(number)
                }
            }
        }
    }

    JackQueenKingCardNumber.NUMBER_RANGE.toList().forAll { number ->
        given("J,Q,K에 해당하는 숫자($number)가 주어졌다") {
            `when`("해당 숫자로 CardNumber를 만들면") {
                then("JackQueenKingCardNumber가 생성된다") {
                    CardNumber.of(number) shouldBe JackQueenKingCardNumber(number)
                }
            }
        }
    }
})
