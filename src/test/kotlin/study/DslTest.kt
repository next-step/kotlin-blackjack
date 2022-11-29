package study

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    internal fun introduce() {
        val person: Person = introduce {
            name("김신영")
        }

        person.name shouldBe "김신영"
    }

    @Test
    internal fun company() {
        val person: Person = introduce {
            name("김신영")
            company("회사")
        }

        person.name shouldBe "김신영"
        person.company shouldBe "회사"
    }

    @Test
    internal fun skills() {
        val person: Person = introduce {
            name("김신영")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "김신영"
        person.company shouldBe "회사"
        person.skills shouldContainExactlyInAnyOrder listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
    }

    @Test
    internal fun languages() {
        val person: Person = introduce {
            name("김신영")
            company("회사")
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

        person.name shouldBe "김신영"
        person.company shouldBe "회사"
        person.skills shouldContainExactlyInAnyOrder listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
        person.languages shouldContainExactlyInAnyOrder listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person =
    PersonBuilder().apply(block).build()

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>?,
    val languages: List<Language>?,
)

class PersonBuilder {
    private lateinit var name: String

    private var company: String? = null

    private var skills: List<Skill>? = null

    private var languages: List<Language>? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

sealed class Skill

data class Soft(private val skill: String) : Skill()
data class Hard(private val skill: String) : Skill()

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Soft(value))
    }
    fun hard(value: String) {
        skills.add(Hard(value))
    }

    fun build() = skills.toList()
}

class LanguageBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build() = languages.toList()
}

data class Language(private val language: String, private val level: Int)
