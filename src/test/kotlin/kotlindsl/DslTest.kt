package kotlindsl

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

infix fun <T> List<T>.isSameElements(other: List<T>) = this.size == other.size &&
    this.toSet() == other.toSet()

class DslTest {
    @ValueSource(strings = ["남상윤", "오스틴"])
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
            name("남상윤")
            company("TDD,클린코드")
        }
        assertThat(person.name).isEqualTo("남상윤")
        assertThat(person.company).isEqualTo("TDD,클린코드")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("남상윤")
            company("TDD,클린코드")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
            }
            skills {
                hard("Kotlin")
            }
        }
        person.name shouldBe "남상윤"
        person.company shouldBe "TDD,클린코드"
        assertThat(
            person.skills isSameElements listOf(
                Soft("A passion for problem solving"),
                Soft("Good communication skills"),
                Hard("Kotlin"),
            ),
        ).isTrue
    }

    @Test
    fun languages() {
        val person = introduce {
            name("남상윤")
            company("TDD,클린코드")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
            languages {
                "Chinese" level 1
            }
        }
        assertThat(person.name).isEqualTo("남상윤")
        assertThat(person.company).isEqualTo("TDD,클린코드")
        assertThat(
            person.skills isSameElements listOf(
                Soft("A passion for problem solving"),
                Soft("Good communication skills"),
                Hard("Kotlin"),
            ),
        ).isTrue
        assertThat(
            person.languages isSameElements listOf(
                Language("Korean", 5),
                Language("English", 3),
                Language("Chinese", 1),
            ),
        ).isTrue
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: MutableList<Skill> = mutableListOf()
    private var languages: MutableList<Language> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills.addAll(SkillBuilder().apply(block).build())
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages.addAll(LanguageBuilder().apply(block).build())
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class LanguageBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages.toList()
    }
}

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList(),
)

interface Skill {
    val name: String
}
data class Hard(override val name: String) : Skill
data class Soft(override val name: String) : Skill

data class Language(val name: String, val level: Int)
