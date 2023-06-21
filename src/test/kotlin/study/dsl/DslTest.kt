package study.dsl

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["홍길동", "박규동"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("박규동")
            company("활빈당")
        }
        person.name shouldBe "박규동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("박규동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "박규동"
        person.company shouldBe "활빈당"
        person.skills shouldBe listOf(
            Skill("soft", "A passion for problem solving"),
            Skill("soft", "Good communication skills"),
            Skill("hard", "Kotlin")
        )
    }

    @Test
    fun languages() {
        val person = introduce {
            name("박규동")
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
        person.name shouldBe "박규동"
        person.company shouldBe "활빈당"
        person.skills shouldBe listOf(
            Skill("soft", "A passion for problem solving"),
            Skill("soft", "Good communication skills"),
            Skill("hard", "Kotlin")
        )
        person.languages shouldBe listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val languages: List<Language>
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills: MutableList<Skill> = mutableListOf()
    private val languages: MutableList<Language> = mutableListOf()

    fun name(name: String) {
        this.name = name
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills.addAll(SkillBuilder().apply(block).build())
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages.addAll(LanguageBuilder().apply(block).build())
    }

    fun build(): Person = Person(name, company, skills, languages)
}
