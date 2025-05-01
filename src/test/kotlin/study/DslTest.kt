package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    /*
     * introduce {
     *     name("Seokjin")
     * }
     * */
    @ValueSource(strings = ["Seokjin", "Kang"])
    @ParameterizedTest
    fun `name test`(input: String) {
        val person: Person =
            introduce {
                name(input)
            }

        assertThat(person.name).isEqualTo(input)
    }

    /*
     * introduce {
     *     company("dh")
     * }
     * */
    @Test
    fun `company test`() {
        val person: Person =
            introduce {
                name("Seokjin")
                company("dh")
            }

        assertThat(person.name).isEqualTo("Seokjin")
        assertThat(person.company).isEqualTo("dh")
    }

    /*
     * introduce {
     *     skills {
     *         soft("A passion for problem solving")
     *         soft("Good communication skills")
     *         hard("Kotlin")
     *     }
     * }
     */
    @Test
    fun `skills test`() {
        val person: Person =
            introduce {
                name("Seokjin")
                company("dh")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        val expectedSoft = listOf("A passion for problem solving", "Good communication skills")
        val expectedHard = listOf("Kotlin")

        assertThat(person.name).isEqualTo("Seokjin")
        assertThat(person.company).isEqualTo("dh")
        assertThat(person.skills?.soft).hasSameElementsAs(expectedSoft)
        assertThat(person.skills?.hard).hasSameElementsAs(expectedHard)
    }

    /*
     * introduce {
     *     languages {
     *         "Korean" level 3
     *         "English" level 3
     *     }
     * }
     * */
    @Test
    fun `languages test`() {
        val person: Person =
            introduce {
                name("Seokjin")
                company("dh")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                    "Korean" level 3
                    "English" level 3
                }
            }
        val expectedSoft = listOf("A passion for problem solving", "Good communication skills")
        val expectedHard = listOf("Kotlin")

        assertThat(person.name).isEqualTo("Seokjin")
        assertThat(person.company).isEqualTo("dh")
        assertThat(person.skills?.soft).hasSameElementsAs(expectedSoft)
        assertThat(person.skills?.hard).hasSameElementsAs(expectedHard)
        assertThat(person.languages?.levels!!["Korean"]).isEqualTo(3)
        assertThat(person.languages.levels["English"]).isEqualTo(3)
    }

    private fun introduce(block: PersonBuilder.() -> Unit): Person {
//        return PersonBuilder().apply { block() }.build()
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
            skills = SkillsBuilder().apply(block).build()
        }

        fun languages(block: LanguageBuilder.() -> Unit) {
            languages = LanguageBuilder().apply(block).build()
        }

        fun build(): Person = Person(name, company, skills, languages)
    }

    class SkillsBuilder {
        private val soft = mutableListOf<String>()
        private val hard = mutableListOf<String>()

        fun soft(value: String) {
            soft.add(value)
        }

        fun hard(value: String) {
            hard.add(value)
        }

        fun build(): Skills = Skills(soft, hard)
    }

    class LanguageBuilder {
        private val languages = mutableMapOf<String, Int>()

        infix fun String.level(other: Int) {
            languages[this] = other
        }

        fun build(): Languages = Languages(languages)
    }

    data class Person(val name: String, val company: String?, val skills: Skills?, val languages: Languages?)

    data class Skills(val soft: List<String>, val hard: List<String>)

    data class Languages(val levels: Map<String, Int>)
}
