package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("민동연")
 *   company("우아한형제들")
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
    @Test
    fun nameTest() {
        val person =
            introduce {
                this.name("민동연")
            }
        person.name shouldBe "민동연"
    }

    @ValueSource(strings = ["민동연", "홍길동"])
    @ParameterizedTest
    fun nameTest2(name: String) {
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
                name("민동연")
                company("우아한형제들")
            }
        person.company shouldBe "우아한형제들"
    }

    @Test
    fun skillsTest() {
        val person =
            introduce {
                name("민동연")
                company("우아한형제들")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        person.company shouldBe "우아한형제들"
    }

    @Test
    fun languageTest() {
        val person =
            introduce {
                name("민동연")
                company("우아한형제들")
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
        person.company shouldBe "우아한형제들"
    }
}

private fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
//    val person = Person("")
//    person.block()
//    return person
}

// 힌트: immutable list를 갖게 될 것이다.
private data class Person(val name: String, val company: String?, val skills: List<String>?, val languages: List<String>?)

// 힌트: mutable list를 갖게 될 것이다.
private class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: MutableList<String>? = null
    private var languages: MutableList<String>? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build().toMutableList()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build().toMutableList()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

private class SkillsBuilder {
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

private class LanguagesBuilder {
    private val languages = mutableListOf<String>()

    infix fun String.level(level: Int) {
        languages.add("$this: $level")
    }

    fun build(): List<String> {
        return languages
    }
}

// data class Person(var name: String = "") {
//    fun name(value: String) {
//        name = value
//    }
// }
