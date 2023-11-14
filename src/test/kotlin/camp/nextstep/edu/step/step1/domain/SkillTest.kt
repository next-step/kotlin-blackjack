package camp.nextstep.edu.step.step1.domain

import camp.nextstep.edu.step.step1.builder.SkillBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName

@DisplayName("스킬은")
class SkillTest : BehaviorSpec({

    Given("스킬들이 주어지고") {
        val skillsList = listOf("Kotlin", "Java")

        When("스킬을 생성하면") {
            val skills = introduceSkill {
                hard(skillsList[0])
                soft(skillsList[1])
            }

            Then("스킬이 생성된다") {
                skills shouldBe listOf(Skill.HardSkill("Kotlin"), Skill.SoftSkill("Java"))
            }
        }
    }

    Given("만약 스킬들 이름이 비어있고") {
        val blankSkillList = listOf("", "")

        When("스킬을 생성하면") {
            val exceptionSoftSkillByBlankName = shouldThrow<IllegalArgumentException> {
                introduceSkill {
                    soft(blankSkillList[1])
                }
            }

            val exceptionHardSkillByBlankName = shouldThrow<IllegalArgumentException> {
                introduceSkill {
                    hard(blankSkillList[0])
                }
            }

            Then("예외가 발생한다.") {
                exceptionSoftSkillByBlankName.message shouldBe "소프트 스킬 이름이 입력되지 않았습니다."
                exceptionHardSkillByBlankName.message shouldBe "하드 스킬 이름이 입력되지 않았습니다."
            }
        }
    }

})


fun introduceSkill(block: SkillBuilder.() -> Unit): List<Skill> {
    return SkillBuilder().apply(block).build()
}
