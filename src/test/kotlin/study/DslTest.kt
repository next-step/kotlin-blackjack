package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

/**
 * introduce {
 *   name("박경철")
 *   company("우아한형제들")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 3
 *   }
 * }
 */
class DslTest : StringSpec({
    "사람 이름을 설정한다." {
        listOf(
            "박경철" to "박경철",
            "홍길동" to "홍길동",
        )
            .forAll { (name, expected) ->
                introduce { name(name) }.name shouldBe expected
            }
    }

    "사람, 회사 이름을 설정한다." {
        val person = introduce {
            name("박경철")
            company("우아한형제들")
        }

        person.company shouldBe "우아한형제들"
    }

    "스킬 정보를 설정한다." {
        val person = introduce {
            name("박경철")
            company("우아한형제들")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.softSkills shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.hardSkills shouldBe listOf("Kotlin")
    }
})

private fun introduce(builder: PersonBuilder.() -> Unit): Person =
    PersonBuilder().apply(builder).build()

private class Person(
    val name: String,
    val company: String?,
    val softSkills: List<String>,
    val hardSkills: List<String>,
)

private class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var softSkills: List<String> = emptyList()
    private var hardSkills: List<String> = emptyList()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(builder: SkillsBuilder.() -> Unit) {
        val skillsBuilder = SkillsBuilder().apply(builder)
        this.softSkills = skillsBuilder.softSkills
        this.hardSkills = skillsBuilder.hardSkills
    }

    fun build(): Person =
        Person(
            name = this.name,
            company = this.company,
            softSkills = this.softSkills,
            hardSkills = this.hardSkills
        )
}

private class SkillsBuilder {
    val softSkills = mutableListOf<String>()
    val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }
}

