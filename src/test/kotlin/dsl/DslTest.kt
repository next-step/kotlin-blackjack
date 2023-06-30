package dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

//introduce {
//    name("박재성")
//    company("우아한형제들")
//    skills {
//        soft("A passion for problem solving")
//        soft("Good communication skills")
//        hard("Kotlin")
//    }
//    languages {
//        "Korean" level 5
//        "English" level 3
//    }
//}
class DslTest {

    @ValueSource(strings = ["정석준", "Dino"])
    @ParameterizedTest
    internal fun introduce(value: String) {
        val person: Person = introduce {
            name = value
        }
        person.name shouldBe value
        person.company shouldBe null
    }

    @Test
    internal fun company() {
        val person = introduce {
            name = "정석준"
            company = "비바리퍼블리카"
        }
        person.name shouldBe "정석준"
        person.company shouldBe "비바리퍼블리카"
    }

    @Test
    internal fun skill() {
        val person = introduce {
            name = "정석준"
            company = "비바리퍼블리카"
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "정석준"
        person.company shouldBe "비바리퍼블리카"
        person.skill?.softSkills shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skill?.hardSkills shouldBe listOf("Kotlin")
    }

    @Test
    internal fun language() {
        val person = introduce {
            name = "정석준"
            company = "비바리퍼블리카"
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
        person.name shouldBe "정석준"
        person.company shouldBe "비바리퍼블리카"
        person.skill?.softSkills shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skill?.hardSkills shouldBe listOf("Kotlin")
        person.language?.languages shouldBe mapOf("Korean" to 5, "English" to 3)
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skill: Skill?,
    val language: Language? = null,
)

class PersonBuilder {
    var name: String = ""
    var company: String? = null
    var skill: Skill? = null
    var language: Language? = null

    fun build(): Person = Person(name, company, skill, language)
}

inline fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

data class Skill(
    val softSkills: List<String>,
    val hardSkills: List<String>,
)

class SkillBuilder {
    val softSkills = mutableListOf<String>()
    val hardSkills = mutableListOf<String>()

    fun soft(skill: String) {
        softSkills.add(skill)
    }

    fun hard(skill: String) {
        hardSkills.add(skill)
    }

    fun build(): Skill = Skill(softSkills, hardSkills)
}

inline fun PersonBuilder.skills(block: SkillBuilder.() -> Unit): Skill {
    val skill = SkillBuilder().apply(block).build()
    this.skill = skill
    return skill
}

data class Language(
    val languages: Map<String, Int>,
)

class LanguageBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Language = Language(languages)
}

inline fun PersonBuilder.languages(block: LanguageBuilder.() -> Unit): Language {
    val language = LanguageBuilder().apply(block).build()
    this.language = language
    return language
}
