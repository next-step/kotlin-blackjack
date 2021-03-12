package study

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person: Person = introduce {
            name("송태헌")
        }
        assertThat(person.name).isEqualTo("송태헌")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("송태헌")
            company("회사")
        }
        assertThat(person.name).isEqualTo("송태헌")
        assertThat(person.company).isEqualTo("회사")
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("송태헌")
            company("회사")
            skills {
                hard("kotlin")
                soft("A passion for problem solving")
                soft("Good communication skills")
            }
        }
        assertThat(person.name).isEqualTo("송태헌")
        assertThat(person.company).isEqualTo("회사")
        assertThat(person.skills.values).contains(
            Hard("kotlin"), Soft("A passion for problem solving"), Soft("Good communication skills")
        )
    }

    @Test
    fun lauguages() {
        val person: Person = introduce {
            name("송태헌")
            company("회사")
            skills {
                hard("kotlin")
                soft("A passion for problem solving")
                soft("Good communication skills")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.name).isEqualTo("송태헌")
        assertThat(person.company).isEqualTo("회사")
        assertThat(person.skills.values).contains(
            Hard("kotlin"), Soft("A passion for problem solving"), Soft("Good communication skills")
        )
        assertThat(person.languages.values).contains(
            entry("Korean", 5), entry("English", 3)
        )
    }
}

fun introduce(initialize: Person.() -> Unit): Person {
    return Person().apply(initialize)
}

class Skills {
    var values: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        values.add(Hard(name))
    }

    fun soft(name: String) {
        values.add(Soft(name))
    }
}
