package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
  name("안경무")
  company("카카오")
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

* */

class DslTest {
    @ValueSource(strings = ["안경무", "브랜"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("안경무")
            company("카카오")
        }
        assertThat(person.name).isEqualTo("안경무")
        assertThat(person.company).isEqualTo("카카오")
    }

    @Test
    fun `skill`() {
        val person: Person = introduce {
            name("안경무")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.skills?.soft?.get(0)?.title).isEqualTo("A passion for problem solving")
        assertThat(person.skills?.soft?.get(1)?.title).isEqualTo("Good communication skills")
        assertThat(person.skills?.hard?.get(0)?.title).isEqualTo("Kotlin")
    }

    @Test
    fun `language`() {
        val person = introduce {
            name("안경무")
            company("카카오")
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
        assertThat(person.languages?.languages?.get(0)?.name).isEqualTo("Korean")
        assertThat(person.languages?.languages?.get(0)?.level).isEqualTo(5)
        assertThat(person.languages?.languages?.get(1)?.name).isEqualTo("English")
        assertThat(person.languages?.languages?.get(1)?.level).isEqualTo(3)
    }
}
