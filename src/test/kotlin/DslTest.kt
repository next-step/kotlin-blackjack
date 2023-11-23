import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["정현준", "박현준"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("정현준")
            company("워트")
        }
        assertThat(person.name).isEqualTo("정현준")
        assertThat(person.company).isEqualTo("워트")
    }

    @Test
    fun test() {
        val actual = introduce {
            name("정현준")
            company("워트")
            skills {
                soft("문제 해결 능력")
                soft("소통 능력")
                hard("코틀린")
            }
            languages {
                "Korean" level 5
                "English" level 1
            }
        }

        val expected = Person(
            name = "정현준",
            company = "워트",
            skills = Skills(
                soft = listOf(Skill.Soft("문제 해결 능력"), Skill.Soft("소통 능력")),
                hard = listOf(Skill.Hard("코틀린"))
            ),
            languages = Languages(
                listOf(Language("Korean", 5), Language("English", 1))
            )
        )

        assertThat(actual).isEqualTo(expected)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
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

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build() = Languages(languages)
}

class SkillsBuilder {
    private val soft = mutableListOf<Skill>()
    private val hard = mutableListOf<Skill>()

    fun soft(name: String) {
        soft.add(Skill.Soft(name))
    }

    fun hard(name: String) {
        hard.add(Skill.Hard(name))
    }

    fun build(): Skills = Skills(soft, hard)
}

data class Skills(
    val soft: List<Skill>,
    val hard: List<Skill>
)

sealed class Skill {
    data class Hard(val name: String) : Skill()
    data class Soft(val name: String) : Skill()
}

data class Languages(val languages: List<Language>)
data class Language(val name: String, val level: Int)
data class Person(val name: String, val company: String?, val skills: Skills?, val languages: Languages?)
