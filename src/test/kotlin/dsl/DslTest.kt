package dsl

import dsl.domain.Person
import dsl.domain.PersonBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Created by Jaesungchi on 2022.05.31..
 */
class DslTest {
    @ValueSource(strings = ["박재성", "제이슨"])
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
            name("박재성")
            company("우아한형제들")
        }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("박재성")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.skills?.softs?.firstOrNull()?.name).isEqualTo("A passion for problem solving")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("박재성")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages?.languages?.firstOrNull()?.name).isEqualTo("Korean")
    }

    @Test
    fun fullTest() {
        val person = introduce {
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
        assertThat(person.skills?.softs?.firstOrNull()?.name).isEqualTo("A passion for problem solving")
        assertThat(person.languages?.languages?.firstOrNull()?.name).isEqualTo("Korean")
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
