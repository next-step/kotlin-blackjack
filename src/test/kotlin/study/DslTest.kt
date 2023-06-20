package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("박재성")
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
class DslTest {

    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "추종현"])
    fun introduce(value: String) {
        val person: Person = introduce() {
            name(value)
        }
        person.name shouldBe value
        person.company shouldBe null
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.skills[0].value shouldBe "A passion for problem solving"
        person.skills[1].value shouldBe "Good communication skills"
        person.skills[2].value shouldBe "Kotlin"
    }

    @Test
    fun languages() {
        val person = introduce {
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
        person.languages[0].name shouldBe "Korean"
        person.languages[0].level shouldBe 5
        person.languages[1].name shouldBe "English"
        person.languages[1].level shouldBe 3
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String,
    var company: String? = null,
    val skills: MutableList<Skill> = mutableListOf(),
    val languages: MutableList<Language> = mutableListOf()
)

class PersonBuilder() {
    private lateinit var name: String
    private var company: String? = null
    private var skills: MutableList<Skill> = mutableListOf()
    private var languages: MutableList<Language> = mutableListOf()

    fun name(name: String) {
        this.name = name
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

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class Skill(val value: String)

class SkillsBuilder() {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Skill(value))
    }

    fun hard(value: String) {
        skills.add(Skill(value))
    }


    fun build(): MutableList<Skill> {
        return skills
    }
}

class Language(val name: String, val level: Int)

class LanguagesBuilder() {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): MutableList<Language> {
        return languages
    }
}
