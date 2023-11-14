import io.kotest.matchers.shouldBe
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.Languages
import study.LanguagesBuilder
import study.Skills
import study.SkillsBuilder

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 3
 *   }
 * }
 */

class DslTest {
    @ValueSource(strings = ["홍길동", "영재"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
            company("블루버드")
            softSkill("A passion for problem solving")
            softSkill("Good communication skills")
            hardSkill("Kotlin")
            language("Korean" to 5)
            language("English" to 3)
        }

        person.name shouldBe name
        person.company shouldBe "블루버드"
        person.skills shouldBe Skills(
            listOf("A passion for problem solving", "Good communication skills"),
            listOf("Kotlin")
        )
        println(person)
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val skills = Skills(
            softSkill = listOf("A passion for problem solving", "Good communication skills"),
            hardSkill = listOf("Kotlin")
        )
        val softExpect = listOf("A passion for problem solving", "Good communication skills")
        val hardExpect = listOf("Kotlin")
        skills.softSkill shouldBe softExpect
        skills.hardSkill shouldBe hardExpect
    }

    @Test
    fun pair() {
        val pair1 = Pair(1, "one")
        val pair2 = 1 to "one"
        val pair3 = 1 of "one"
    }

    private infix fun Int.of(s: String): Pair<Int, String> = Pair(this, s)
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills = SkillsBuilder()
    private val languages = LanguagesBuilder()
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun softSkill(value: String) {
        skills.softSkills(value)
    }

    fun hardSkill(value: String) {
        skills.hardSkills(value)
    }

    fun language(language: Pair<String, Int>) {
        languages.addLanguages(language)
    }

    fun build(): Person {
        return Person(name, company, skills.build(), languages.build())
    }
}

data class Person(val name: String, val company: String?, val skills: Skills, val languages: Languages)
