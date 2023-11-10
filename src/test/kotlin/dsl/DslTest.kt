package dsl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DslTest : FunSpec({
    test("DSL 테스트") {
        val name = "홍길동"
        val company = "홍길동전"

        val person = introduce {
            name("홍길동")
            company("홍길동전")
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

        person.name shouldBe name
        person.company shouldBe company
        person.skills?.size shouldBe 3
        person.languages?.size shouldBe 2
    }
})

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: MutableList<String>? = null
    private var languages: MutableMap<String, Int>? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: PersonBuilder.() -> Unit) {
        skills = mutableListOf()
        apply(block)
    }

    fun soft(value: String) {
        skills?.add(value)
    }

    fun hard(value: String) {
        skills?.add(value)
    }

    fun languages(block: PersonBuilder.() -> Unit) {
        languages = mutableMapOf()
        apply(block)
    }

    infix fun String.level(level: Int) {
        languages?.set(this, level)
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Person(val name: String, val company: String?, val skills: List<String>?, val languages: Map<String, Int>?)
