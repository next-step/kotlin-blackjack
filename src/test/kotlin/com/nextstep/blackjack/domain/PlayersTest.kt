package com.nextstep.blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayersTest : BehaviorSpec({
    Given("빈값이 포함된 이름 리스트가 주어지고") {
        When("Players를 생성하면") {
            Then("예외가 발생한다") {
                shouldThrow<IllegalArgumentException> { Players(" ", "이름", "") } shouldHaveMessage "이름은 빈 값일 수 없습니다."
            }
        }
    }

    Given("이름 리스트가 주어지고") {
        When("Players를 생성하면") {
            Then("리스트에 담긴 각 이름을 가진 Player들이 생성된다") {
                val players = Players("수진", "쪼밀리")

                players.names().size shouldBe 2
                players.names() shouldContainExactly listOf("수진", "쪼밀리")
            }
        }
    }
})
