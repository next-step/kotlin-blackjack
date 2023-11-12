package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["김정우", "제이슨"])
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
            name("김정우")
            company("토스")
        }
        assertThat(person.name).isEqualTo("김정우")
        assertThat(person.company).isEqualTo("토스")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("김정우")
            company("토스")
            skills {
                soft("커뮤니케이션")
                hard("코틀린")
            }
        }
        assertThat(person.name).isEqualTo("김정우")
        assertThat(person.company).isEqualTo("토스")
        assertThat(person.skills).containsExactly(Soft("커뮤니케이션"), Hard("코틀린"))
    }

    @Test
    fun languages() {
        val person = introduce {
            name("김정우")
            company("토스")
            languages {
                "코틀린" level 5
                "자바" level 3
            }
        }
        assertThat(person.name).isEqualTo("김정우")
        assertThat(person.company).isEqualTo("토스")
        assertThat(person.languages).containsExactly(Language("코틀린", 5), Language("자바", 3))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skillBuilder = SkillBuilder()
    private val languageBuilder = LanguageBuilder()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skillBuilder.apply(block)
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languageBuilder.apply(block)
    }

    fun build(): Person {
        return Person(name, company, skillBuilder.build(), languageBuilder.build())
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList()
)

sealed class Skill

data class Soft(val desc: String) : Skill()
data class Hard(val desc: String) : Skill()


class SkillBuilder {
    private val softSkills = mutableListOf<Soft>()
    private val hardSkills = mutableListOf<Hard>()

    fun soft(desc: String) {
        softSkills.add(Soft(desc))
    }

    fun hard(desc: String) {
        hardSkills.add(Hard(desc))
    }

    fun build(): List<Skill> {
        return softSkills + hardSkills
    }
}

class LanguageBuilder {
    private val languages = mutableListOf<Language>()
    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages
    }

}

data class Language(val name: String, val level: Int) : Skill()
