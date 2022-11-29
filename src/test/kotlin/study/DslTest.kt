package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/**
 introduce {
 name("김동인")
 company("ep")
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
 */
class DslTest {

    @Test
    fun introduce() {
        val person: Person = introduce {
            name("김동인")
        }
        person.name shouldBe "김동인"
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("김동인")
            company("ep")
        }
        person.name shouldBe "김동인"
        person.company shouldBe "ep"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("김동인")
            company("ep")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "김동인"
        person.company shouldBe "ep"
        person.skills shouldBe Skills(
            soft = listOf(
                Soft("A passion for problem solving"),
                Soft("Good communication skills")
            ),
            hard = listOf(Hard("Kotlin"))
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("김동인")
            company("ep")
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
        person.name shouldBe "김동인"
        person.company shouldBe "ep"
        person.skills shouldBe Skills(
            soft = listOf(
                Soft("A passion for problem solving"),
                Soft("Good communication skills")
            ),
            hard = listOf(Hard("Kotlin"))
        )
        person.languages shouldBe Languages(languages = listOf(Language("Korean", 5), Language("English", 3)))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    val person = PersonBuilder().apply {
        block()
    }
    return person.build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: Skills = Skills()
    private var languages: Languages = Languages()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        val skills = SkillBuilder().apply {
            block()
        }
        this.skills = skills.build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        val languages = LanguagesBuilder().apply {
            block()
        }
        this.languages = languages.build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillBuilder {
    private val soft = mutableListOf<Soft>()
    private val hard = mutableListOf<Hard>()

    fun soft(value: String) {
        soft.add(Soft(value))
    }

    fun hard(value: String) {
        hard.add(Hard(value))
    }

    fun build(): Skills {
        return Skills(soft.toList(), hard.toList())
    }
}

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(language = this, level = level))
    }

    fun build(): Languages {
        return Languages(languages = languages.toList())
    }
}

data class Person(val name: String, val company: String, val skills: Skills, val languages: Languages)
data class Languages(val languages: List<Language> = listOf())
data class Language(val language: String, val level: Int)
data class Skills(val soft: List<Soft> = listOf(), val hard: List<Hard> = listOf())

@JvmInline
value class Soft(private val value: String) {
    init {
        require(value.isNotBlank()) { "빈 문자열은 들어올 수 없습니다" }
    }
}

@JvmInline
value class Hard(private val value: String) {
    init {
        require(value.isNotBlank()) { "빈 문자열은 들어올 수 없습니다" }
    }
}
