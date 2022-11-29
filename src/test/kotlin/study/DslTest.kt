package study

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
  name("조수진") <- this.가 생략된 것
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
*/
class DslTest {

    @Test
    fun introduce() {
        val person: Person = introduce {
            name("조수진")
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
        person.name shouldBe "조수진"
        person.company shouldBe "우아한형제들"
        person.skills.softSkills shouldContainAll listOf("A passion for problem solving", "Good communication skills")
        person.skills.hardSkills shouldContainAll listOf("Kotlin")
        person.languages shouldContainExactlyInAnyOrder listOf(Language("Korean", 5), Language("English", 3))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(val name: String, val company: String?, val skills: Skills, val languages: List<Language>)
class PersonBuilder {
    lateinit var name: String
    var company: String? = null
    private lateinit var skills: Skills
    private lateinit var languages: List<Language>

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

data class Skills(val softSkills: List<String>, val hardSkills: List<String>)
class SkillsBuilder {
    private var softSkills: MutableList<String> = mutableListOf()
    private var hardSkills: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        this.softSkills.add(value)
    }

    fun hard(value: String) {
        this.hardSkills.add(value)
    }
    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}

data class Language(val name: String, val level: Int)
class LanguagesBuilder {
    private var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages
    }
}
