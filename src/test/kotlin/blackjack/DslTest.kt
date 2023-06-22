package blackjack

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("최진호")
 *   company("패스트뷰")
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
    @ValueSource(strings = ["최진호", "홍길동"])
    @ParameterizedTest
    fun introduce() {
        val person: Person = introduce() {
            name("최진호")
        }
        person.name shouldBe "최진호"
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("최진호")
            company("패스트뷰")
        }
        person.name shouldBe "최진호"
        person.company shouldBe "패스트뷰"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("최진호")
            company("패스트뷰")
            skills {
                soft("spring")
                hard("kotlin")
            }
        }
        person.name shouldBe "최진호"
        person.company shouldBe "패스트뷰"
        person.skills?.soft?.shouldContain("spring")
        person.skills?.hard?.shouldContain("kotlin")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("최진호")
            company("패스트뷰")
            languages {
                "kotlin" level 5
                "spring" level 1
            }
        }
        person.name shouldBe "최진호"
        person.company shouldBe "패스트뷰"
        person.language shouldNotBe null
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(val name: String, val company: String?, val skills: Skills?, val language: List<Language>?)

class Skills(val soft: List<String>, val hard: List<String>)

class SkillsBuilder {
    private var soft: MutableList<String> = mutableListOf()
    private var hard: MutableList<String> = mutableListOf()

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

class Language(val language: String, val level: Int)

class LanguageBuilder {
    private var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages.toList()
    }
}
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: List<Language>? = null
    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
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
