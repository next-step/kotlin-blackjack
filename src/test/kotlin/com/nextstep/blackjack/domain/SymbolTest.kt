package com.nextstep.blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SymbolTest : FunSpec({
    context("Symbol") {
        test("문양 개수 확인") {
            Symbol.values().size shouldBe 4
        }
    }
})
