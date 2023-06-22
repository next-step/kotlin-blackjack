package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("박재성")
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

    @ValueSource(strings = ["홍길동, 임꺽정"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        person.name shouldBe value
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
        val person = introduce {
            name("홍길동")
            company("활빈당")

            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills?.softs?.size shouldBe 2
        person.skills?.hards?.size shouldBe 1
    }

    @Test
    fun languages() {
        val person = introduce {
            name("홍길동")
            company("활빈당")

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
        person.languages?.size shouldBe 2
        person.languages?.get(0)?.first shouldBe "Korean"
        person.languages?.get(0)?.second shouldBe 5
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: List<Pair<String, Int>>? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private var softs: MutableList<String> = mutableListOf()
    private var hards: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softs.add(value)
    }

    fun hard(value: String) {
        hards.add(value)
    }

    fun build(): Skills {
        return Skills(softs, hards)
    }
}

class LanguagesBuilder {

    infix fun String.level(other: Int) = languages.add(Pair(this, other))

    private var languages: MutableList<Pair<String, Int>> = mutableListOf()

    fun build(): List<Pair<String, Int>> {
        return languages
    }
}

data class Skills(val softs: List<String> = listOf(), val hards: List<String> = listOf())

data class Person(val name: String, val company: String?, val skills: Skills?, val languages: List<Pair<String, Int>>?)
