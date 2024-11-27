package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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
    @ValueSource(strings = ["홍길동", "수퍼비"])
    @ParameterizedTest
    fun name(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
            }
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        person.skills.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills.hard shouldBe listOf("Kotlin")
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(val name: String, val company: String?, val skills: Skills)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(value: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(value).build()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}

data class Skills(
    val soft: List<String> = emptyList(),
    val hard: List<String> = emptyList(),
)

class SkillsBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(skill: String) {
        soft.add(skill)
    }

    fun hard(skill: String) {
        hard.add(skill)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}
