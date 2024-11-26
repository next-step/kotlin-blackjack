package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["홍길동", "홍", "길동"])
    @ParameterizedTest
    fun nameTest(name: String) {
        val person =
            introduce {
                name(name)
            }.build()
        person.name shouldBe name
    }

    @Test
    fun companyTest() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
            }.build()
        person.company shouldBe "활빈당"
    }

    @Test
    fun skillsTest() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }.build()

        person.skills.size shouldBe 3
        person.skills[0] shouldBe "soft: A passion for problem solving"
        person.skills[1] shouldBe "soft: Good communication skills"
        person.skills[2] shouldBe "hard: Kotlin"
    }

    @Test
    fun languageTest() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                language {
                    "Korean" level 5
                    "English" level 3
                }
            }.build()

        person.languages.size shouldBe 2
        person.languages[0].name shouldBe "Korean"
        person.languages[0].level shouldBe 5
        person.languages[1].name shouldBe "English"
        person.languages[1].level shouldBe 3
    }
}

fun introduce(block: PersonBuilder.() -> Unit): PersonBuilder {
    return PersonBuilder().apply(block)
}

data class Person(val name: String, val company: String?, val skills: List<String>, val languages: List<Language>)

data class Language(val name: String, val level: Int)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills = mutableListOf<String>()
    private val languages = mutableListOf<Language>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        val skillsBuilder = SkillsBuilder()
        skillsBuilder.apply(block)
        skills.addAll(skillsBuilder.build())
    }

    fun language(block: LanguagesBuilder.() -> Unit) {
        val languagesBuilder = LanguagesBuilder()
        languagesBuilder.apply(block)
        languages.addAll(languagesBuilder.build())
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private val skills = mutableListOf<String>()

    fun soft(description: String) {
        skills.add("soft: $description")
    }

    fun hard(description: String) {
        skills.add("hard: $description")
    }

    fun build(): List<String> {
        return skills
    }
}

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages
    }
}
