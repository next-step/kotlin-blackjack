package study

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
  name("김성빈")
  company("LINE")
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
            name("김성빈")
        }
        person.name shouldBe "김성빈"
        person.company.shouldBeNull()
        person.skills shouldHaveSize 0
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("김성빈")
            company("LINE")
        }
        person.name shouldBe "김성빈"
        person.company shouldBe "LINE"
        person.skills shouldHaveSize 0
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("김성빈")
            company("LINE")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "김성빈"
        person.company shouldBe "LINE"
        person.skills shouldHaveSize 3

        person.addSkill("java")
        person.skills shouldHaveSize 4

        (person.skills as MutableList).add("python")
        person.skills shouldHaveSize 4
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("김성빈")
            company("LINE")
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
        person.name shouldBe "김성빈"
        person.company shouldBe "LINE"
        person.skills shouldHaveSize 3
        person.languages shouldHaveSize 2

        person.addLanguage("German" to 1)
        person.languages shouldHaveSize 3

        (person.languages as MutableList).add("Spanish" to 4)
        person.languages shouldHaveSize 3
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<String> = emptyList()
    private var languages: List<Pair<String, Int>> = emptyList()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillBuilder(private val skills: MutableList<String> = mutableListOf()) {
    fun soft(softSkill: String) {
        skills.add(softSkill)
    }

    fun hard(hardSkill: String) {
        skills.add(hardSkill)
    }

    fun build(): List<String> {
        return skills.toList()
    }
}

class LanguageBuilder(private val languages: MutableList<Pair<String, Int>> = mutableListOf()) {

    infix fun String.level(level: Int) {
        languages.add(Pair(this, level))
    }

    fun build(): List<Pair<String, Int>> {
        return languages.toList()
    }
}

class Person(val name: String, val company: String?, skills: List<String>, languages: List<Pair<String, Int>>) {
    private val _skills: MutableList<String> = skills.toMutableList()
    private val _languages: MutableList<Pair<String, Int>> = languages.toMutableList()
    val skills: List<String>
        get() = _skills.toList()
    val languages: List<Pair<String, Int>>
        get() = _languages.toList()

    fun addSkill(skill: String) {
        _skills.add(skill)
    }

    fun addLanguage(language: Pair<String, Int>) {
        _languages.add(language)
    }
}
