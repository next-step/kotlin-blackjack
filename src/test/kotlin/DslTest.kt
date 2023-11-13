import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun dslTest() {
        val person = introduce {
            name("greentea.latte")
            company("kakao")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        with(person) {
            name shouldBe "greentea.latte"
            company shouldBe "kakao"
            softSkills shouldContainInOrder listOf(
                Skill.SoftSkill("A passion for problem solving"),
                Skill.SoftSkill("Good communication skills"),
            )
            hardSkills shouldContain Skill.HardSkill("Kotlin")
            languageLevels shouldContainInOrder listOf(
                "Korean" to 5,
                "English" to 3,
            )
        }
    }

}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String

    private val softSkills = mutableListOf<Skill.SoftSkill>()
    private val hardSkills = mutableListOf<Skill.HardSkill>()

    private val languageLevels = mutableListOf<Pair<String, Int>>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: PersonBuilder.() -> Unit): PersonBuilder {
        return this.apply(block)
    }

    fun soft(value: String) {
        softSkills.add(Skill.SoftSkill(value))
    }

    fun hard(value: String) {
        hardSkills.add(Skill.HardSkill(value))
    }

    fun languages(block: PersonBuilder.() -> Unit): PersonBuilder {
        return this.apply(block)
    }

    infix fun String.level(s: Int) {
        languageLevels.add(Pair(this, s))
    }

    fun build(): Person {
        return Person(
            name,
            company,
            softSkills,
            hardSkills,
            languageLevels,
        )
    }
}

data class Person(
    val name: String,
    val company: String?,
    val softSkills: List<Skill.SoftSkill>,
    val hardSkills: List<Skill.HardSkill>,
    val languageLevels: List<Pair<String, Int>>,
)

sealed interface Skill {
    data class SoftSkill(val name: String) : Skill
    data class HardSkill(val name: String) : Skill
}