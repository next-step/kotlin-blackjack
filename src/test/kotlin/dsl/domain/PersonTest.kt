package dsl.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class PersonTest : BehaviorSpec({
    val name = "사용자"
    val company = "회사명"
    val skills = Skills(listOf(Soft("테스트 작성능력"), Hard("스프링 개발 능력")))
    val languages = Languages(listOf(Language("korean", 1)))

    Given("사용자 명이 비어있다") {
        val blankName = " "
        When("해당 정보로 사용자를 생성하면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Person(blankName, company, skills, languages) }
            }
        }
    }

    Given("회사명이 공백이다") {
        val blankCompany = " "
        When("해당 정보로 사용자를 생성하면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Person(name, blankCompany, skills, languages) }
            }
        }
    }

    Given("정상적인 정보가 주어졌다") {
        When("해당 정보로 사용자를 생성하면") {
            Then("에러가 던져지지 않는다") {
                shouldNotThrow<Throwable> { Person(name, company, skills, languages) }
            }
        }
    }
})
