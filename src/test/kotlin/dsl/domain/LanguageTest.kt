package dsl.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class LanguageTest : BehaviorSpec({
    val name = "korean"
    val level = 1
    Given("비어있는 언어명이 주어졌다") {
        val blankName = " "
        When("해당 언어명으로 Language를 생성하면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Language(blankName, level) }
            }
        }
    }

    Given("언어 수준이 0이하가 주어졌다") {
        val zeroLevel = 0
        When("해당 언어 수준으로 Language를 생성하면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Language(name, zeroLevel) }
            }
        }
    }

    Given("정상적인 정보가 주어졌다") {
        When("해당 정보로 Language를 생성하면") {
            Then("에러가 던져지지 않는다") {
                shouldNotThrow<Throwable> { Language(name, level) }
            }
        }
    }
})
