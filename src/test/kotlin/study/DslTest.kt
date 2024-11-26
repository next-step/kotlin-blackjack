package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("이규원")
 *   company("한빛앤")
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
 *
 */
class DslTest {
    @ValueSource(strings = ["홍길동", "이규원"])
    @ParameterizedTest
    fun name(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
        person.company shouldBe null
    }

    @ValueSource(strings = ["마키나락스", "한빛앤"])
    @ParameterizedTest
    fun company(company: String) {
        val person =
            introduce {
                name("이규원")
                company(company)
            }
        person.company shouldBe company
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("이규원")
                company("한빛앤")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        person.skills?.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills?.hard shouldBe listOf("Kotlin")
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}

data class Skills(
    val soft: List<String>,
    val hard: List<String>,
)

class SkillsBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}
