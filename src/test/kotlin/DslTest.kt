import io.kotest.inspectors.shouldForAny
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun company() {
        val person =
            introduce {
                name("김영준")
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
        person.name shouldBe "김영준"
        person.company shouldBe "회사"

        val skills = person.skills!!
        skills.soft.shouldForAny { it shouldBe "A passion for problem solving" }
        skills.soft.shouldForAny { it shouldBe "Good communication skills" }
        skills.hard.shouldForAny { it shouldBe "Kotlin" }

        val languages = person.languages!!
        languages.values.shouldForAny {
            it.language shouldBe "Korean"
            it.level shouldBe 5
        }
        languages.values.shouldForAny {
            it.language shouldBe "English"
            it.level shouldBe 3
        }
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Person.Skills? = null
    private var languages: Person.Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }

    class SkillBuilder {
        private val soft: MutableList<String> = mutableListOf()
        private val hard: MutableList<String> = mutableListOf()

        fun soft(skill: String) = soft.add(skill)

        fun hard(skill: String) = hard.add(skill)

        fun build(): Person.Skills {
            return Person.Skills(soft, hard)
        }
    }

    class LanguageBuilder {
        private val languages: MutableList<Person.Language> = mutableListOf()

        infix fun String.level(level: Int) {
            languages.add(Person.Language(this, level))
        }

        fun build(): Person.Languages {
            return Person.Languages(languages)
        }
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
) {
    data class Skills(
        val soft: List<String>,
        val hard: List<String>,
    )

    data class Languages(
        val values: List<Language>,
    )

    data class Language(
        val language: String,
        val level: Int,
    )
}
