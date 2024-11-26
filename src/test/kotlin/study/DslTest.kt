package study

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
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
    @ValueSource(strings = ["홍길동", "양영근"])
    @ParameterizedTest
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
                name("홍길동")
                company("활빈당")
            }
        person.company shouldBe "활빈당"
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }

        person.languages shouldHaveSize 2
        person.languages shouldContainExactlyInAnyOrder listOf(Language("Korean", 5), Language("English", 3))
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
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

        person.skills shouldHaveSize 3
        person.skills shouldContainExactlyInAnyOrder
            listOf(
                Skill(SkillType.SOFT, "A passion for problem solving"),
                Skill(SkillType.SOFT, "Good communication skills"),
                Skill(SkillType.HARD, "Kotlin"),
            )
    }
}

private fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList(),
)

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

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills.addAll(SkillsBuilder().apply(block).build())
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages.addAll(LanguagesBuilder().apply(block).build())
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Skill(val type: SkillType, val description: String)

enum class SkillType {
    SOFT,
    HARD,
}

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(description: String) {
        skills.add(Skill(SkillType.SOFT, description))
    }

    fun hard(description: String) {
        skills.add(Skill(SkillType.HARD, description))
    }

    fun build(): List<Skill> {
        return skills
    }
}

data class Language(val name: String, val level: Int)

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages
    }
}
