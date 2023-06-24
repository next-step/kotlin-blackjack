package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class PlayersTest : FunSpec({

    context("init") {
        test("플레이어가 아무도 없으면 생성되지 않는다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Players(listOf()) }
            exception.message shouldBe "플레이어는 최소 1명이 되어야한다."
        }
    }

    context("from") {
        test("플레이어의 이름을 받아 생성할 수 있다.") {
            val actual = Players.from(listOf("최진영"))
            actual.values shouldHaveSize 1
        }
    }
})
