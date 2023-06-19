package domain

import dsl.domain.Skill
import dsl.domain.SkillType
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class SkillTest : BehaviorSpec({
    given("능력설명이 비어있다") {
        val description = "   "
        `when`("해당 정보로 Skill을 생성하면") {
            then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Skill(description, SkillType.SOFT) }
            }
        }
    }

    given("능력 설명이 정상적으로 들어온다") {
        val description = "테스트 작성능력"
        `when`("해당 정보로 Skill을 생성하면") {
            then("에러가 던져지지 않는다") {
                shouldNotThrow<Throwable> { Skill(description, SkillType.SOFT) }
            }
        }
    }
})
