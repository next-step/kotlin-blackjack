package com.nextstep.blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardNumberTest : FunSpec({
    context("CardNumber") {
        test("카드 숫자 개수 확인") {
            CardNumber.values().size shouldBe 13
        }

        test("카드 숫자기호에 해당하는 값 확인") {
            listOf(
                CardNumber.ACE to 1,
                CardNumber.TWO to 2,
                CardNumber.THREE to 3,
                CardNumber.FOUR to 4,
                CardNumber.FIVE to 5,
                CardNumber.SIX to 6,
                CardNumber.SEVEN to 7,
                CardNumber.EIGHT to 8,
                CardNumber.NINE to 9,
                CardNumber.TEN to 10,
                CardNumber.JACK to 10,
                CardNumber.QUEEN to 10,
                CardNumber.KING to 10
            ).forAll { numberByCard ->
                run {
                    numberByCard.first.number shouldBe numberByCard.second
                }
            }
        }
    }
})
