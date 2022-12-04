package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
  name("홍길동")
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
 */
class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("홍길동")
        }
        person.name shouldBe "홍길동"
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("홍길동")
            company("회사")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "회사"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("홍길동")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.skills.soft[0] shouldBe "A passion for problem solving"
        person.skills.soft[1] shouldBe "Good communication skills"
        person.skills.hard[0] shouldBe "Kotlin"
    }

    @Test
    fun language() {
        val person: Person = introduce {
            name("홍길동")
            company("회사")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.language.levels[0].language shouldBe "Korean"
        person.language.levels[0].level shouldBe 5
        person.language.levels[1].language shouldBe "English"
        person.language.levels[1].level shouldBe 3
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(val name: String, val company: String?, val skills: Skills, val language: Languages)
data class Skills(val soft: List<String>, val hard: List<String>)
data class Level(val language: String, val level: Int)
data class Languages(val levels: List<Level>)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills(emptyList(), emptyList())
    private var languages: Languages = Languages(emptyList())

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private var soft: List<String> = emptyList()
    private var hard: List<String> = emptyList()

    fun soft(value: String) {
        val mutableList = soft.toMutableList()
        mutableList.add(value)
        soft = mutableList
    }

    fun hard(value: String) {
        val mutableList = hard.toMutableList()
        mutableList.add(value)
        hard = mutableList
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

class LanguageBuilder {
    private var levels: List<Level> = emptyList()

    infix fun String.level(level: Int) {
        val mutableList = levels.toMutableList()
        mutableList.add(Level(this, level))
        levels = mutableList
    }

    fun build(): Languages {
        return Languages(levels)
    }
}
