package study.dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author 이상준
 */
class DslTest {
    @Test
    fun name() {
        val person =
            introduce {
                name("홍길동")
            }
        person.name shouldBe "홍길동"
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("박재성")
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
        person.skills[0].hard shouldBe "Kotlin"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("박재성")
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
        person.skills[0].hard shouldBe "Kotlin"
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("박재성")
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
        person.languages[0].name shouldBe "Korean"
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String = "",
    val skills: List<Skill> = emptyList(),
    val languages: List<Language> = emptyList(),
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: MutableList<Skill> = mutableListOf()
    private var languages: List<Language> = listOf()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills.add(SkillBuilder().apply(block).build())
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Skill(
    val soft: String,
    val hard: String,
)

class SkillBuilder {
    private lateinit var soft: String
    private lateinit var hard: String

    fun soft(value: String) {
        this.soft = value
    }

    fun hard(value: String) {
        this.hard = value
    }

    fun build(): Skill {
        return Skill(soft, hard)
    }
}

data class Language(
    val name: String,
    val level: Int,
)

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages
    }
}
