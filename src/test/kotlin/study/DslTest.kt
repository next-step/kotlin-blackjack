package study

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun introduce() {
        val person: Person = introduce {
            name("이도원")
        }
        person.name shouldBe "이도원"
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("이도원")
            company("새회사")
        }
        person.name shouldBe "이도원"
        person.company shouldBe "새회사"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("이도원")
            company("새회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "이도원"
        person.company shouldBe "새회사"
        person.skills shouldContainExactly listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("이도원")
            company("새회사")
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
        person.name shouldBe "이도원"
        person.company shouldBe "새회사"
        person.skills shouldContainExactly listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
        person.language shouldContainExactly mapOf(
            "Korean" to 5,
            "English" to 3
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class LanguageBuilder {
    private val languages: MutableMap<String, Int> = mutableMapOf()
    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun build(): Map<String, Int> {
        return languages.toMap()
    }
}

sealed class Skill
data class Soft(val value: String) : Skill()
data class Hard(val value: String) : Skill()
class SkillsBuilder() {
    private var skills: MutableList<Skill> = mutableListOf()

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

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill> = listOf()
    private var languages: Map<String, Int> = mapOf()

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

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val language: Map<String, Int>
)
