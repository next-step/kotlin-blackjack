package study

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("서준수")
        }
        person.name shouldBe "서준수"
        person.company shouldBe null
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("서준수")
            company("우아한형제들")
        }
        person.name shouldBe "서준수"
        person.company shouldBe "우아한형제들"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("서준수")
            company("우아한형제들")
            skill {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "서준수"
        person.company shouldBe "우아한형제들"
        person.skills shouldContainExactlyInAnyOrder listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("서준수")
            company("우아한형제들")
            skill {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            language {
                "Korean" level 5
                "English" level 3
            }
        }

        person.name shouldBe "서준수"
        person.company shouldBe "우아한형제들"
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

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
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

    fun skill(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun language(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Person(val name: String, val company: String?, val skills: List<Skill>, val languages: Map<String, Int>)

sealed class Skill
data class Hard(val hard: String) : Skill()
data class Soft(val soft: String) : Skill()

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun hard(value: String) {
        skills.add(Hard(value))
    }

    fun soft(value: String) {
        skills.add(Soft(value))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}

class LanguageBuilder {
    private var languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(other: Int) {
        languages[this] = other
    }

    fun build(): Map<String, Int> {
        return languages
    }
}
