package resume

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

// introduce {
//     name("박재성")
//     company("우아한형제들")
//     skills {
//         soft ("A passion for problem solving")
//         soft ("Good communication skills")
//         hard ("Kotlin")
//     }
//     languages {
//         "Korean" level 5
//         "English" level 3
//     }
// }
class ResumeTest {
    @Test
    internal fun testName() {
        val person: Person = introduce {
            name("김광수")
        }

        assertThat(person.name).isEqualTo("김광수")
    }

    @Test
    internal fun company() {
        val person: Person = introduce {
            company("마진좋아")
        }
        assertThat(person.company).isEqualTo("마진좋아")
    }

    @Test
    internal fun skills() {
        val person: Person = introduce {
            skills {
                hard("kotlin")
                soft("Good communication skills")
            }
        }
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("kotlin"),
            Soft("Good communication skills")
        )
    }

    private fun introduce(initializer: Person.() -> Unit): Person {
        return Person().apply(initializer)
    }

    class Person {
        lateinit var name: String
            private set

        var company: String? = null
            private set

        lateinit var skills: Skills

        fun name(name: String) {
            this.name = name
        }

        fun company(company: String) {
            this.company = company
        }

        fun skills(initializer: Skills.() -> Unit) {
            skills = Skills().apply(initializer)
        }
    }

    class Skills {
        private val skills: MutableList<Skill> = mutableListOf()

        fun hard(name: String) {
            skills.add(Hard(name))
        }

        fun soft(name: String) {
            skills.add(Soft(name))
        }

        fun toList(): List<Skill> {
            return skills
        }
    }
}

sealed class Skill

data class Hard(val name: String) : Skill()

data class Soft(val name: String) : Skill()
