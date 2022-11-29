package study

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
  name("홍종표")
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

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null

    private val skillsBuilder: SkillsBuilder = SkillsBuilder()
    private val languagesBuilder: LanguagesBuilder = LanguagesBuilder()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }


    fun skills(block: SkillsBuilder.() -> Unit) {
        skillsBuilder.apply(block)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languagesBuilder.apply(block)
    }


    fun build(): Person {
        return Person(name, company, skillsBuilder.build(), languagesBuilder.build())
    }
}

data class Person(val name: String, val company: String?, val skills: List<Skill>, val languages: Map<String, Int>)

sealed class Skill
data class Soft(val value: String) : Skill()
data class Hard(val value: String) : Skill()

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Soft(value))
    }

    fun hard(value: String) {
        skills.add(Hard(value))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}

class LanguagesBuilder {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Map<String, Int> {
        return languages
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}


class DslTest {
    @Test
    internal fun introduce() {
        val person: Person = introduce {
            name("홍종표")
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

        person.name shouldBe "홍종표"
        person.company shouldBe "회사"
        person.skills shouldContainExactlyInAnyOrder listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )

        person.languages shouldContainExactly mapOf(
            "Korean" to 5,
            "English" to 3
        )

    }
}
