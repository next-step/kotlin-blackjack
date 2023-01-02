package study

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import study.SkillType.HARD
import study.SkillType.SOFT

/*
introduce {
  name("이우찬")
  company("회사")
  skills {
    soft("A passion for problem solving")
    soft("Good communication skills")
    hard("Kotlin")
  } // mutable in builder -> immutable
  languages {
    "Korean" level 5
    "English" level 3
  } // infix 활용
}
 */
class DslTest {
    @Test
    internal fun introduce() {
        val person: Person = introduce {
            name("이우찬")
        }
        person.name shouldBe "이우찬"
        person.company.shouldBeNull()
    }

    @Test
    internal fun company() {
        val person: Person = introduce {
            name("이우찬")
            company("회사")
        }
        person.name shouldBe "이우찬"
        person.company shouldBe "회사"
        person.skills.shouldBeNull()
    }

    @Test
    internal fun skills() {
        val person: Person = introduce {
            name("이우찬")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "이우찬"
        person.company shouldBe "회사"
        person.skills shouldContainExactlyInAnyOrder listOf(
            Skill(SOFT, "A passion for problem solving"),
            Skill(SOFT, "Good communication skills"),
            Skill(HARD, "Kotlin")
        )
        person.languages.shouldBeNull()
    }

    @Test
    internal fun languages() {
        val person: Person = introduce {
            name("이우찬")
            company("회사")
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

        person.name shouldBe "이우찬"
        person.company shouldBe "회사"
        person.skills shouldContainExactlyInAnyOrder listOf(
            Skill(SOFT, "A passion for problem solving"),
            Skill(SOFT, "Good communication skills"),
            Skill(HARD, "Kotlin")
        )
        person.languages shouldContainExactlyInAnyOrder listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder() {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?
)

class SkillsBuilder() {
    private var skills: MutableList<Skill> = mutableListOf()

    fun soft(description: String) {
        skills.add(Skill(SOFT, description))
    }

    fun hard(description: String) {
        skills.add(Skill(HARD, description))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}

data class Skills(val value: List<Skill>) : List<Skill> by value
data class Skill(val type: SkillType, val description: String)
enum class SkillType { HARD, SOFT }

class LanguagesBuilder() {
    private var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages)
    }
}

data class Languages(val value: List<Language>) : List<Language> by value
data class Language(val locale: String, val level: Int)
