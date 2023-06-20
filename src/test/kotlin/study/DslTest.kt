package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("이수민")
 *   company("카카오")
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
class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "이수민"])
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }

        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("이수민")
            company("카카오")
        }

        person.name shouldBe "이수민"
        person.company shouldBe "카카오"
        person.skills.shouldBeNull()
    }

    @Test
    fun skills() {
        val person = introduce {
            name("이수민")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "이수민"
        person.company shouldBe "카카오"
        person.skills?.value shouldBe listOf(Skill("soft", "A passion for problem solving"), Skill("soft", "Good communication skills"), Skill("hard", "Kotlin"))
        person.languages.shouldBeNull()
    }

    @Test
    fun languages() {
        val person = introduce {
            name("이수민")
            company("카카오")
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

        person.name shouldBe "이수민"
        person.company shouldBe "카카오"
        person.skills?.value shouldBe listOf(Skill("soft", "A passion for problem solving"), Skill("soft", "Good communication skills"), Skill("hard", "Kotlin"))
        person.languages?.value shouldBe listOf(Language("Korean", 5), Language("English", 3))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(val name: String, val company: String?, val skills: Skills?, val languages: Languages?)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Skill(val level: String, val description: String)

class Skills(val value: List<Skill>)

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(description: String) {
        skills.add(Skill("soft", description))
    }

    fun hard(description: String) {
        skills.add(Skill("hard", description))
    }

    fun build(): Skills {
        return Skills(this.skills)
    }
}

data class Language(val type: String, val level: Int)

class Languages(val value: List<Language>)

class LanguagesBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(other: Int) {
        languages.add(Language(this, other))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}