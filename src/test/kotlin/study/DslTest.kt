package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
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

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills.hard shouldBe listOf("Kotlin")
        person.languages.languages shouldBe listOf("Korean" to 5, "English" to 3)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(value: String) {
        name = value
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

class LanguagesBuilder {
    private val languages = mutableListOf<Pair<String, Int>>()

    infix fun String.level(level: Int) {
        languages.add(this to level)
    }

    fun build(): Languages {
        return Languages(languages)
    }
}

class SkillsBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: Languages
)

data class Skills(val soft: List<String>, val hard: List<String>)

data class Languages(val languages: List<Pair<String, Int>>)