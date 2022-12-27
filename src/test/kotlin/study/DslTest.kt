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
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill> = emptyList()
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

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(softSkill: String) {
        skills.add(Soft(softSkill))
    }

    fun hard(hardSkill: String) {
        skills.add(Hard(hardSkill))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}

class LanguageBuilder {
    private val languages: MutableList<Pair<String, Int>> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Pair(this, level))
    }

    fun build(): List<Pair<String, Int>> {
        return languages.toList()
    }
}

class Person(val name: String, val company: String?, val skills: List<Skill>, val languages: List<Pair<String, Int>>)

sealed interface Skill
data class Soft(val softSkill: String) : Skill
data class Hard(val hardSkill: String) : Skill
