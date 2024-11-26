package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
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

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills.soft.size shouldBe 2
        person.skills.hard.size shouldBe 1
        person.languages.languages.size shouldBe 2
    }
}

private fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String? = null,
    val skills: Skills,
    val languages: Languages,
)

data class Skills(
    var soft: List<String> = emptyList(),
    var hard: List<String> = emptyList(),
) {
    fun soft(skill: String) {
        this.soft += skill
    }

    fun hard(skill: String) {
        this.hard += skill
    }
}

data class Languages(
    var languages: List<Language> = emptyList(),
) {
    infix fun String.level(level: Int) {
        languages += Language(this, level)
    }

    data class Language(
        val name: String,
        val level: Int,
    )
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages
    private var company: String? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: Skills.() -> Unit) {
        this.skills = Skills().apply(block)
    }

    fun languages(block: Languages.() -> Unit) {
        this.languages = Languages().apply(block)
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
