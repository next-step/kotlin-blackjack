import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.Languages
import study.LanguagesBuilder
import study.Skills
import study.SkillsBuilder

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
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
    @ValueSource(strings = ["홍길동", "영재"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
            company("블루버드")
            skills(introduceSkill {
                softSkills("A passion for problem solving")
                softSkills("Good communication skills")
                hardSkills("Kotlin")
            })
            language(introduceLanguage {
                language("Korean" to 5)
                language("English" to 3)
            })
        }

        println(person)
    }

    @Test
    fun pair() {
        val pair1 = Pair(1, "one")
        val pair2 = 1 to "one"
        val pair3 = 1 of "one"
    }

    private infix fun Int.of(s: String): Pair<Int, String> = Pair(this, s)
}

fun introduceLanguage(block: LanguagesBuilder.() -> Unit): Languages {
    return LanguagesBuilder().apply(block).build()
}

fun introduceSkill(block: SkillsBuilder.() -> Unit): Skills {
    return SkillsBuilder().apply(block).build()
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: Skills
    private lateinit var languages: Languages
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(skill: Skills) {
        skills = skill
    }

    fun language(language: Languages) {
        languages = language
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Person(val name: String, val company: String?, val skills: Skills, val languages: Languages)
