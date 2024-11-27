package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["이희상", "희상"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person =
            introduce {
                name(value)
            }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("이희상")
                company("비밀")
            }
        assertThat(person.name).isEqualTo("이희상")
        assertThat(person.company).isEqualTo("비밀")
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("이희상")
                company("비밀이에요")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        assertThat(person.name).isEqualTo("이희상")
        assertThat(person.company).isEqualTo("비밀이에요")
        assertThat(person.skills.soft).containsExactly("A passion for problem solving", "Good communication skills")
        assertThat(person.skills.hard).containsExactly("Kotlin")
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("이희상")
                company("비밀이에요")
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
        assertThat(person.name).isEqualTo("이희상")
        assertThat(person.company).isEqualTo("비밀이에요")
        assertThat(person.skills.soft).containsExactly("A passion for problem solving", "Good communication skills")
        assertThat(person.skills.hard).containsExactly("Kotlin")
        assertThat(person.language.map.get("Korean")).isEqualTo(5)
        assertThat(person.language.map.get("English")).isEqualTo(3)
    }

    fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }

    class PersonBuilder {
        private lateinit var name: String
        private var company: String? = null
        private var skills: Skills = Skills(listOf(), listOf())
        private var language: Languages = Languages(mapOf())

        fun name(value: String) {
            name = value
        }

        fun company(value: String) {
            company = value
        }

        fun skills(block: SkillsBuilder.() -> Unit) {
            skills = SkillsBuilder().apply(block).build()
        }

        fun languages(block: LanguagesBuilder.() -> Unit) {
            language = LanguagesBuilder().apply(block).build()
        }

        fun build(): Person {
            return Person(name, company, skills, language)
        }
    }

    data class Person(val name: String, val company: String?, val skills: Skills, val language: Languages)

    class SkillsBuilder() {
        val soft: MutableList<String> = mutableListOf()
        val hard: MutableList<String> = mutableListOf()

        fun soft(skill: String) {
            soft.add(skill)
        }

        fun hard(skill: String) {
            hard.add(skill)
        }

        fun build(): Skills {
            return Skills(soft.toList(), hard.toList())
        }
    }

    class Skills(soft: List<String>, hard: List<String>) {
        val soft: List<String> = soft
            get() = field.toList()
        val hard: List<String> = hard
            get() = field.toList()
    }

    class LanguagesBuilder() {
        val map: MutableMap<String, Int> = mutableMapOf()

        infix fun String.level(other: Int) = map.put(this, other)

        fun build(): Languages {
            return Languages(map.toMap())
        }
    }

    class Languages(map: Map<String, Int>) {
        val map: Map<String, Int> = map.toMap()
            get() = field.toMap()
    }
}
