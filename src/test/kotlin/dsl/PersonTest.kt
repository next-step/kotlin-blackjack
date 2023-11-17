package dsl

import Person
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import person

class PersonTest {

    @Test
    fun personTest() {
        val person: Person = person {
            name("박재성")
            company("우아한형제들")
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

        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
        assertThat(person.skills?.get(0)?.desc).isEqualTo("A passion for problem solving")
        assertThat(person.skills?.get(1)?.desc).isEqualTo("Good communication skills")
        assertThat(person.skills?.get(2)?.desc).isEqualTo("Kotlin")
        assertThat(person.languages?.get(0)?.kind).isEqualTo("Korean")
        assertThat(person.languages?.get(0)?.level).isEqualTo(5)
        assertThat(person.languages?.get(1)?.kind).isEqualTo("English")
        assertThat(person.languages?.get(1)?.level).isEqualTo(3)
    }
}
