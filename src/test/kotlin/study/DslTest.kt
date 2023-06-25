package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
introduce {
name("안태선")
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
 */
class DslTest {
    @ValueSource(strings = ["안태선", "제이슨"])
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
            name("안태선")
            company("카카오")
        }
        person.name shouldBe "안태선"
        person.company shouldBe "카카오"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("안태선")
            company("카카오")
            skills {
                soft("소프트")
                hard("하드")
            }
        }
        with(person.skills) {
            softSkills shouldBe listOf("소프트")
            hardSkills shouldBe listOf("하드")
        }
    }

    @Test
    fun languages() {
        val person = introduce {
            name("안태선")
            company("카카오")
            skills {
                soft("소프트")
                hard("하드")
            }
            languages {
                "korean" level 5
                "english" level 4
            }
        }
        person.languages shouldBe listOf(
            Language("korean", 5),
            Language("english", 4)
        )
    }
}

/**
 * Person에 있는 함수만 이용하겠다는 것을 말한다.
 */
fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String,
    val company: String? = null,
    val skills: Skills = Skills(emptyList(), emptyList()),
    val languages: List<Language> = emptyList()

)

data class Skills(val softSkills: List<String>, val hardSkills: List<String>)
data class Language(val name: String, val level: Int)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills(emptyList(), emptyList())
    private var languages: List<Language> = emptyList()

    fun name(name: String) {
        this.name = name
    }

    fun company(value: String) {
        company = value
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

class SkillsBuilder {
    var softSkills: MutableList<String> = mutableListOf()
    var hardSkills: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}

class LanguageBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): List<Language> {
        return languages
    }
}
