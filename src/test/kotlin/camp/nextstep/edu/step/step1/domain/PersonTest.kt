package camp.nextstep.edu.step.step1.domain

import camp.nextstep.edu.step.step1.builder.PersonBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName

@DisplayName("사람은")
class PersonTest : BehaviorSpec({

    Given("이름, 회사, 스킬, 언어들이 주어지면") {
        val personName = "Joe"
        val companyName = "NextStep"
        val skills = listOf("Kotlin", "Java", "Javascript")
        val languages = listOf("Korean", "English")

        When("사람이라는 도메인을 생성하면") {
            val person = introducePerson {
                name(name = personName)
                company { name(name = companyName) }
                skill { hard(skills[0]) }
                skill { hard(skills[1]) }
                skill { soft(skills[2]) }
                language { languages[0].level(5) }
                language { languages[1].level(3) }
            }

            Then("사람이라는 도메인이 생성된다") {
                person.name shouldBe "Joe"
                person.getCompanyName() shouldBe "NextStep"
                person.skill[0].introduce shouldBe "Kotlin"
                person.skill[1].introduce shouldBe "Java"
                person.skill[2].introduce shouldBe "Javascript"
                person.language[0].name shouldBe "Korean"
                person.language[0].level shouldBe 5
                person.language[1].name shouldBe "English"
                person.language[1].level shouldBe 3

            }
        }
    }

    Given("만약 빈 이름이 주어지고") {
        val blankPersonName = ""
        val companyName = "NextStep"
        val skills = listOf("Kotlin", "Java", "Javascript")
        val languages = listOf("Korean", "English")

        When("생성을 요청하면") {
            val exceptionByBlankName = shouldThrow<IllegalArgumentException> {
                introducePerson {
                    name(name = blankPersonName)
                    company { name(name = companyName) }
                    skill { hard(skills[0]) }
                    skill { hard(skills[1]) }
                    skill { soft(skills[2]) }
                    language { languages[0].level(5) }
                    language { languages[1].level(3) }
                }
            }
            Then("예외가 발생한다.") {
                exceptionByBlankName.message shouldBe "이름이 입력되지 않았습니다."
            }
        }
    }

})

fun introducePerson(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
