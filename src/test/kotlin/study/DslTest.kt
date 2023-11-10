package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun dslTest() {
        val person = introduce {
            name("inhyeokk")
            company("kakao")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Koreans" level 5
                "English" level 3
            }
        }

        with(person) {
            name shouldBe "inhyeokk"
            company shouldBe "kakao"
            skills shouldBe listOf(
                Skill.Soft("A passion for problem solving"),
                Skill.Soft("Good communication skills"),
                Skill.Hard("Kotlin")
            )
            languages shouldBe listOf(
                Language("Koreans", 5),
                Language("English", 3)
            )
        }
    }
}

fun introduce(block: Person.Builder.() -> Unit): Person = Person.Builder().apply(block).build()

data class Person(val name: String, val company: String, val skills: Skills, val languages: Languages) {

    class Builder {
        private var name: String = ""
        private var company: String = ""
        private var skills: Skills = Skills(emptyList())
        private var languages: Languages = Languages(emptyList())

        fun name(name: String) {
            this.name = name
        }

        fun company(company: String) {
            this.company = company
        }

        fun skills(block: Skills.Builder.() -> Unit) {
            this.skills = Skills.Builder().apply(block).build()
        }

        fun languages(block: Languages.Builder.() -> Unit) {
            this.languages = Languages.Builder().apply(block).build()
        }

        fun build(): Person = Person(name, company, skills, languages)
    }
}

@JvmInline
value class Skills(private val skills: List<Skill>) : List<Skill> by skills {
    class Builder {
        private val skills = mutableListOf<Skill>()

        fun soft(skill: String) {
            skills.add(Skill.Soft(skill))
        }

        fun hard(skill: String) {
            skills.add(Skill.Hard(skill))
        }

        fun build() = Skills(skills)
    }
}

sealed class Skill(open val skill: String) {
    data class Soft(override val skill: String) : Skill(skill)
    data class Hard(override val skill: String) : Skill(skill)
}

@JvmInline
value class Languages(private val languages: List<Language>) : List<Language> by languages {
    class Builder {
        private val languages = mutableListOf<Language>()

        infix fun String.level(level: Int) {
            languages.add(Language(this, level))
        }

        fun build() = Languages(languages)
    }
}

data class Language(val name: String, val level: Int)
