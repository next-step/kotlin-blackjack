package study

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("김민성")
            company("천명앤컴퍼니")
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

        person.name shouldBe "김민성"
        person.company shouldBe "천명앤컴퍼니"
        person.skills.soft shouldContainExactlyInAnyOrder listOf("A passion for problem solving", "Good communication skills")
        person.skills.hard shouldContainExactlyInAnyOrder listOf("Kotlin")
        person.languages shouldContainExactlyInAnyOrder listOf(Language("Korean", 5), Language("English", 3))
    }
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: List<Language>
    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder()
            .apply(block)
            .build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder()
            .apply(block)
            .build()
    }

    fun build(): Person {
        return Person(
            name = name,
            company = company,
            skills = skills,
            languages = languages
        )
    }
}

class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: List<Language>,
)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder()
        .apply(block)
        .build()
}

data class Skills(
    val soft: List<String>,
    val hard: List<String>,
)

class SkillsBuilder {
    private val soft: MutableList<String> = mutableListOf()
    private val hard: MutableList<String> = mutableListOf()

    fun soft(softSkill: String) {
        soft.add(softSkill)
    }

    fun hard(hardSkill: String) {
        hard.add(hardSkill)
    }

    fun build(): Skills {
        return Skills(soft, hard)
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

data class Language(
    val name: String,
    val level: Int,
)
