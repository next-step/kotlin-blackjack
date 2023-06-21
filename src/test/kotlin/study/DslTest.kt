package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "이승환", "어쩌구"])
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }

        person.name shouldBe value
        person.company shouldBe null
    }

    @Test
    fun company() {
        val person = introduce {
            name("이승환")
            company("카카오")
        }

        person.name shouldBe "이승환"
        person.company shouldBe "카카오"
    }

    @Test
    fun languageAndSkill() {
        val person = introduce {
            name("이승환")
            company("kakao")
            skills {
                soft("Java")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        person.name shouldBe "이승환"
        person.company shouldBe "kakao"
        person.skills!!.softSkills.contains("Java") shouldBe true
        person.skills.hardSkills.contains("Kotlin") shouldBe true
        person.languages!!.skillLevel["Korean"] shouldBe 5
        person.languages.skillLevel["English"] shouldBe 3
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

/**
 * builder 클래스
 */

class PersonBuilder {
    lateinit var name: String
    var company: String? = null
    var skills: Skills? = null
    var language: Languages? = null

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
        language = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, language)
    }
}

class SkillBuilder {
    var softSkills: MutableList<String> = mutableListOf()
    var hardSkills: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}

class LanguageBuilder {
    private val language: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(value: Int) {
        language[this] = value
    }

    fun build(): Languages {
        return Languages(language)
    }
}

/**
 * data 객체
 */
data class Person(val name: String, val company: String?, val skills: Skills?, val languages: Languages?)
data class Skills(val softSkills: List<String>, val hardSkills: List<String>)
data class Languages(val skillLevel: Map<String, Int>)
