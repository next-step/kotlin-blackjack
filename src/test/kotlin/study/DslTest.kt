package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
 *   skills {
 *      soft("A passion for problem solving")
 *      soft("Good communication skills")
 *      hard("Kotlin")
 *   }
 *   languages {
 *      "Korean" level 5
 *      "English" level 3
 *   }
 */
class DslTest {
    @ValueSource(strings = ["홍길동", "조재연"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("조재연")
            company("Google")
        }
        assertThat(person.name).isEqualTo("조재연")
        assertThat(person.company).isEqualTo("Google")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("조재연")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills).containsExactly("A passion for problem solving", "Good communication skills", "Kotlin")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("조재연")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages).containsExactlyEntriesOf(mapOf("Korean" to 5, "English" to 3))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class SkillBuilder {
    private val skills = mutableListOf<String>()

    fun soft(skill: String) {
        skills.add(skill)
    }

    fun hard(skill: String) {
        skills.add(skill)
    }

    fun build(): List<String> = skills.toList()
}

class LanguagesBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Map<String, Int> = languages.toMap()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills = SkillBuilder()
    private val languages = LanguagesBuilder()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills.apply(block)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages.apply(block)
    }

    fun build(): Person {
        return Person(name, company, skills.build(), languages.build())
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<String>,
    val languages: Map<String, Int>
)
