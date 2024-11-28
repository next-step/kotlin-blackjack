package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("신종화")
 *   company("카카오")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 2
 *   }
 * }
 */
class DslTest {
    @ValueSource(strings = ["신종화", "홍길동"])
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
                name("신종화")
                company("카카오")
            }
        person.company shouldBe "카카오"
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("신종화")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        person.skills shouldBe listOf("A passion for problem solving", "Good communication skills", "Kotlin")
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("신종화")
                languages {
                    "Korean" level 5
                    "English" level 2
                }
            }
        person.languages shouldBe mapOf("Korean" to 5, "English" to 2)
    }

    @Test
    fun `Dsl 전체 테스트`() {
        val person =
            introduce {
                name("신종화")
                company("카카오")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                    "Korean" level 5
                    "English" level 2
                }
            }
        person shouldBe
            Person(
                "신종화",
                "카카오",
                listOf("A passion for problem solving", "Good communication skills", "Kotlin"),
                mapOf("Korean" to 5, "English" to 2),
            )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<String>?,
    val languages: Map<String, Int>?,
)

class PersonBuilder() {
    private var name: String? = null
    private var company: String? = null
    private var skills: MutableList<String>? = null
    private var languages: MutableMap<String, Int>? = null

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build().toMutableList()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build().toMutableMap()
    }

    fun build(): Person {
        val name = name ?: throw IllegalArgumentException("이름은 필수입니다.")
        return Person(name, company, skills, languages)
    }
}

/**
 *
 * class PersonBuilder(val name: String) {  // name을 val로 받아서 필수로 초기화
 *     private var company: String? = null
 *     private var skills: MutableList<String>? = null
 *     private var languages: MutableMap<String, Int>? = null
 *
 *     fun company(value: String) {
 *         company = value
 *     }
 *
 *     fun skills(block: SkillsBuilder.() -> Unit) {
 *         skills = SkillsBuilder().apply(block).build().toMutableList()
 *     }
 *
 *     fun languages(block: LanguagesBuilder.() -> Unit) {
 *         languages = LanguagesBuilder().apply(block).build().toMutableMap()
 *     }
 *
 *     fun build(): Person {
 *         return Person(name, company, skills, languages)
 *     }
 * }
 *
 *
 * fun introduce(name: String, block: PersonBuilder.() -> Unit): Person {
 *     return PersonBuilder(name).apply(block).build()
 * }
 *
 */

class SkillsBuilder {
    private val skills = mutableListOf<String>()

    fun soft(value: String) {
        skills.add(value)
    }

    fun hard(value: String) {
        skills.add(value)
    }

    fun build(): List<String> {
        return skills
    }
}

class LanguagesBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Map<String, Int> {
        return languages
    }
}
