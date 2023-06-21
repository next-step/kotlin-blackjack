package dsl.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class PersonTest : BehaviorSpec({
    val name = "사용자"
    val company = "회사명"
    val skills = Skills(listOf(Soft("테스트 작성능력"), Hard("스프링 개발 능력")))
    val languages = Languages(listOf(Language("korean", 1)))

    given("사용자 명이 비어있다") {
        val blankName = " "
        `when`("해당 정보로 사용자를 생성하면") {
            then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Person(blankName, company, skills, languages) }
            }
        }
    }

    given("회사명이 공백이다") {
        val blankCompany = " "
        `when`("해당 정보로 사용자를 생성하면") {
            then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Person(name, blankCompany, skills, languages) }
            }
        }
    }

    given("정상적인 정보가 주어졌다") {
        `when`("해당 정보로 사용자를 생성하면") {
            then("에러가 던져지지 않는다") {
                shouldNotThrow<Throwable> { Person(name, company, skills, languages) }
            }
        }
    }
})
