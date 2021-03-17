package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ResumeTest {
    @Test
    fun name() {
        val person: Person = introduce {
            name("남윤서")
        }

        assertThat(person.name).isEqualTo("남윤서")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("남윤서")
            company("무직")
        }

        assertAll(
            { assertThat(person.name).isEqualTo("남윤서") },
            { assertThat(person.company).isEqualTo("무직") }
        )
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("남윤서")
            company("무직")
            skills {
                soft("먹기")
                soft("자기")
                hard("눕기")
            }
        }

        assertAll(
            { assertThat(person.name).isEqualTo("남윤서") },
            { assertThat(person.company).isEqualTo("무직") },
            { assertThat(person.skills.skills).contains(Soft("먹기"), Soft("자기"), Hard("눕기")) }
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("남윤서")
            company("무직")
            skills {
                soft("먹기")
                soft("자기")
                hard("눕기")
            }
            languages {
                "Korean" level 0
                "English" level -2
            }
        }

        assertAll(
            { assertThat(person.name).isEqualTo("남윤서") },
            { assertThat(person.company).isEqualTo("무직") },
            { assertThat(person.skills.skills).contains(Soft("먹기"), Soft("자기"), Hard("눕기")) },
            {
                assertThat(person.languages.language).contains(
                    Language(Pair("Korean", 0)),
                    Language(Pair("English", -2))
                )
            }
        )
    }

    private fun introduce(initializer: Person.() -> Unit): Person {
        return Person().apply(initializer)
    }

    class Person {
        lateinit var name: String
        var company: String? = null
        lateinit var skills: Skills
        lateinit var languages: Languages

        fun name(name: String) {
            this.name = name
        }

        fun company(company: String) {
            this.company = company
        }

        fun skills(initializer: Skills.() -> Unit) {
            skills = Skills().apply(initializer)
        }

        fun languages(initializer: Languages.() -> Unit) {
            languages = Languages().apply(initializer)
        }
    }

    class Skills {
        private val _skills: MutableList<Skill> = mutableListOf()
        val skills: List<Skill>
            get() = _skills.toList()

        fun hard(name: String) {
            _skills.add(Hard(name))
        }

        fun soft(name: String) {
            _skills.add(Soft(name))
        }
    }

    class Languages {
        private val _languages: MutableList<Language> = mutableListOf()
        val language: List<Language>
            get() = _languages.toList()

        infix fun String.level(level: Int) {
            _languages.add(Language(Pair(this, level)))
        }
    }
}

sealed class Skill
data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()
data class Language(val language: Pair<String, Int>)
