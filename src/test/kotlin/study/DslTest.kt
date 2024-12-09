package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("문장호")
 *   company("howser")
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
 *
 */
class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["문장호", "장"])
    fun name(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("문장호")
                company("howser")
            }
        person.company shouldBe "howser"
    }

    @ParameterizedTest
    @CsvSource("java, jvc, kotlin")
    fun skills(
        softOne: String,
        softTwo: String,
        hard: String,
    ) {
        val person =
            introduce {
                name("문장호")
                company("howser")
                skills {
                    soft(softOne)
                    soft(softTwo)
                    hard(hard)
                }
            }
        person.skills[0].desc shouldBe softOne
        person.skills[1].desc shouldBe softTwo
        person.skills[2].desc shouldBe hard
    }

    @ParameterizedTest
    @CsvSource("Korean, 10", "English, 5")
    fun language(
        language: String,
        level: Int,
    ) {
        val person =
            introduce {
                name("문장호")
                company("howser")
                skills {
                    soft("s1")
                    soft("s2")
                    hard("hard")
                }
                languages {
                    language level level
                }
            }
        person.languages.first().level shouldBe level
    }
}

data class Language(val language: String, val level: Int)

class LanguageBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> = languages
}

data class Skill(val type: String, val desc: String)

class SkillsBuilder {
    private var skills = mutableListOf<Skill>()

    fun soft(description: String) {
        skills.add(Skill("soft", description))
    }

    fun hard(description: String) {
        skills.add(Skill("hard", description))
    }

    fun build(): List<Skill> = skills
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills = mutableListOf<Skill>()
    private var languages = mutableListOf<Language>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills.addAll(SkillsBuilder().apply(block).build())
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages.addAll(LanguageBuilder().apply(block).build())
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val languages: List<Language>,
)
