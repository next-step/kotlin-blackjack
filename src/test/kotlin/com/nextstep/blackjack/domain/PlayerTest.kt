package com.nextstep.blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayerTest : BehaviorSpec({
    Given("빈값의 이름이 주어지고") {
        When("Player를 생성하면") {
            Then("예외가 발생한다") {
                shouldThrow<IllegalArgumentException> { Player(" ") } shouldHaveMessage "이름은 빈 값일 수 없습니다."
            }
        }
    }
})
