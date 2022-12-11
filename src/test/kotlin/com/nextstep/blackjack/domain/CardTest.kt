package com.nextstep.blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    context("Card") {
        test("카드 객체 동일성 확인") {
            val card1 = Card(Symbol.DIAMOND, CardNumber.ACE)
            val card2 = Card(Symbol.DIAMOND, CardNumber.ACE)

            card1 shouldBe card2
        }
    }
})
