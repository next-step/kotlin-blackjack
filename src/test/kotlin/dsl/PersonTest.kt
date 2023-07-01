package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PersonTest : StringSpec({
    "name = 홍길동, company = 활빈당 으로 주어진 경우, person이 올바르게 생성된다." {
        val inputName = "홍길동"
        val inputCompany = "활빈당"
        val person = introduce {
            name(inputName)
            company(inputCompany)
        }
        person.name shouldBe inputName
        person.company shouldBe inputCompany
    }

    "name = 홍길동, skill은 soft('c'), hard('java') 순으로 주어진 경우, person이 올바르게 생성된다." {
        val inputName = "홍길동"
        val inputSoftSkill = "c"
        val inputHardSkill = "java"

        val person = introduce {
            name(inputName)
            skills {
                soft(inputSoftSkill)
                hard(inputHardSkill)
            }
        }
        person.name shouldBe inputName
        person.skills.value.size shouldBe 2
        person.skills[0] shouldBe Skill.soft(inputSoftSkill)
        person.skills[1] shouldBe Skill.hard(inputHardSkill)
    }

    "name = 홍길동, language는 'korean' , level 5 로 주어진 경우, person이 올바르게 생성된다." {
        val inputName = "홍길동"
        val inputLanguage = "korean"
        val inputLevel = 5

        val person = introduce {
            name(inputName)
            languages {
                "korean" level 5
            }
        }
        person.name shouldBe inputName
        person.languages.value.size shouldBe 1
        person.languages[0] shouldBe Language(inputLanguage, inputLevel)
    }
})
