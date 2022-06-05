package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
  name("김진우")
  company("카카오")
  skills {
    soft("A passion for problem solving")
    soft("Good communication skills")
    hard("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 2
  }
}
 */
class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["김진우", "제이슨"])
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("김진우")
            company("카카오")
        }
        assertThat(person.name).isEqualTo("김진우")
        assertThat(person.company).isEqualTo("카카오")
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("김진우")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.name).isEqualTo("김진우")
        assertThat(person.company).isEqualTo("카카오")
        assertThat(person.skills!!.values).containsOnly(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("김진우")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 2
            }
        }
        assertThat(person.name).isEqualTo("김진우")
        assertThat(person.company).isEqualTo("카카오")
        assertThat(person.skills!!.values).containsOnly(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
        assertThat(person.languages!!.values).containsOnly(
            Language("Korean", 5),
            Language("English", 2)
        )
    }
}
