package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/*
introduce {
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
 */

class DslTest {

    @Test
    fun `지금부터 내 소개를 시작하지`() {
        val person: Person = introduce {
            name("최현구")
            company("맘편한세상")
            languages {
                "Kotlin" level 1
                "Java" level 99
            }
        }

        assertThat(person.name).isEqualTo("최현구")
        assertThat(person.company).isEqualTo("맘편한세상")
        assertThat(person.languages.values[0].name).isEqualTo("Kotlin")
        assertThat(person.languages.values[0].level).isEqualTo(1)
        assertThat(person.languages.values[1].name).isEqualTo("Java")
        assertThat(person.languages.values[1].level).isEqualTo(99)
    }

    @Test
    fun nameAndCompany() {
        val person: Person = introduce {
            name("최현구")
            company("맘편한세상")
        }

        assertThat(person.name).isEqualTo("최현구")
        assertThat(person.company).isEqualTo("맘편한세상")
    }

    @Test
    fun language() {
        val person: Person = introduce {
            languages {
                "Kotlin" level 1
                "Java" level 99
            }
        }

        assertThat(person.languages.values[0].name).isEqualTo("Kotlin")
        assertThat(person.languages.values[0].level).isEqualTo(1)
        assertThat(person.languages.values[1].name).isEqualTo("Java")
        assertThat(person.languages.values[1].level).isEqualTo(99)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder()
        .apply(block)
        .build()
}
