package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["홍길동", "박제헌", "박재성", "제이슨", "제프"])
    @ParameterizedTest
    fun name(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @ValueSource(strings = ["카카오", "네이버", "우아한형제들", "구글"])
    @ParameterizedTest
    fun company(value: String) {
        val person: Person = introduce {
            name("박제헌")
            company(value)
        }
        person.name shouldBe "박제헌"
        person.company shouldBe value
    }

    @ValueSource(strings = ["A passion for problem solving", "Good communication skills", "nice presentation"])
    @ParameterizedTest
    fun softSkills(value: String) {
        val person: Person = introduce {
            name("박제헌")
            company("카카오")
            skills {
                soft(value)
            }
        }
        person.name shouldBe "박제헌"
        person.company shouldBe "카카오"
        person.skills!!.soft[0] shouldBe value
    }

    @ValueSource(strings = ["kotlin", "java", "kubernetes", "dkos"])
    @ParameterizedTest
    fun hardSkills(value: String) {
        val person: Person = introduce {
            name("박제헌")
            company("카카오")
            skills {
                hard(value)
            }
        }
        person.name shouldBe "박제헌"
        person.company shouldBe "카카오"
        person.skills!!.hard[0] shouldBe value
    }

    @ValueSource(strings = ["korean", "english", "japanese", "russian"])
    @ParameterizedTest
    fun languages(value: String) {
        val person: Person = introduce {
            name("박제헌")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("kotlin")
            }
            languages {
                value level 5
            }
        }
        person.name shouldBe "박제헌"
        person.company shouldBe "카카오"
        person.skills!!.soft[0] shouldBe "A passion for problem solving"
        person.skills!!.soft[1] shouldBe "Good communication skills"
        person.skills!!.hard[0] shouldBe "kotlin"
        person.languages!![0].lang shouldBe value
        person.languages!![0].level shouldBe 5
    }

    @Test
    fun givenTest() {
        val person: Person = introduce {
            name("박제헌")
            company("카카오")
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
        person.name shouldBe "박제헌"
        person.company shouldBe "카카오"
        person.skills!!.soft[0] shouldBe "A passion for problem solving"
        person.skills!!.soft[1] shouldBe "Good communication skills"
        person.skills!!.hard[0] shouldBe "Kotlin"
        person.languages!![0].lang shouldBe "Korean"
        person.languages!![0].level shouldBe 5
        person.languages!![1].lang shouldBe "English"
        person.languages!![1].level shouldBe 3
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(val name: String, val company: String?, val skills: Skills?, val languages: List<Language>?)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: List<Language>? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
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

class Skills(val soft: List<String>, val hard: List<String>)

class SkillsBuilder {
    private var soft: List<String> = emptyList()
    private var hard: List<String> = emptyList()

    fun soft(value: String) {
        soft = soft + value
    }

    fun hard(value: String) {
        hard = hard + value
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

class Language(val lang: String, val level: Int)

class LanguageBuilder {
    private var languages: List<Language> = emptyList()

    infix fun String.level(level: Int) {
        languages = languages + Language(this, level)
    }
    fun build(): List<Language> {
        return languages
    }
}

