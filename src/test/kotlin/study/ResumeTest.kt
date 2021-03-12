package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person: Person = introduce {
            name("김상구")
        }

        assertThat(person.name).isEqualTo("김상구")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("김상구")
            company("페이민트")
        }

        assertThat(person.name).isEqualTo("김상구")
        assertThat(person.company).isEqualTo("페이민트")
    }

    @Test
    fun hard() {
        val person: Person = introduce {
            name("김상구")
            company("페이민트")
            skills {
                hard("Kotlin")
            }
        }

        assertThat(person.name).isEqualTo("김상구")
        assertThat(person.company).isEqualTo("페이민트")
        assertThat(person.skills.toList()).containsExactly(Hard("Kotlin"))
    }

    @Test
    fun soft() {
        val person: Person = introduce {
            name("김상구")
            company("페이민트")
            skills {
                hard("Kotlin")
                soft("java")
            }
        }

        assertThat(person.name).isEqualTo("김상구")
        assertThat(person.company).isEqualTo("페이민트")
        assertThat(person.skills.toList()).containsExactly(
            Hard("Kotlin"),
            Soft("java")
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("김상구")
            company("페이민트")
            skills {
                hard("Kotlin")
                soft("java")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.name).isEqualTo("김상구")
        assertThat(person.company).isEqualTo("페이민트")
        assertThat(person.skills.toList()).containsExactly(
            Hard("Kotlin"),
            Soft("java")
        )
        assertThat(person.languages.toList()).containsExactly(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply(initializer)
}
