package study

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DslTest {
    @Test
    fun fullIntroduce() {
        val person =
            introduce {
                name("윤주리")
                company("우아한형제들")
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
        assertThat(person.name).isEqualTo("윤주리")
        assertThat(person.company).isEqualTo("우아한형제들")
        assertThat(person.skills.soft).containsExactly("A passion for problem solving", "Good communication skills")
        assertThat(person.skills.hard).containsExactly("Kotlin")
        assertThat(person.languages.items).containsEntry("Korean", 5).containsEntry("English", 3)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()
    private var languages: Languages = Languages()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

class SkillsBuilder {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skills = Skills(softSkills, hardSkills)
}

class LanguagesBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun build(): Languages = Languages(languages)
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills = Skills(),
    val languages: Languages = Languages(),
)

data class Skills(
    val soft: List<String> = emptyList(),
    val hard: List<String> = emptyList(),
)

data class Languages(
    val items: Map<String, Int> = emptyMap(),
)
