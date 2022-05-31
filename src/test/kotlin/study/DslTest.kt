package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["이인동", "austen"])
    fun name(input: String) {
        val person: Person = introduce {
            name(input)
        }
        assertThat(person.name).isEqualTo(input)
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("이인동")
            company("카카오")
        }
        assertThat(person.name).isEqualTo("이인동")
        assertThat(person.company).isEqualTo("카카오")
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("이인동")
            company("카카오")
            skills {
                soft("Android")
                hard("Kotlin")
            }
        }
        assertThat(person.skills).contains(Skill.Soft("Android"))
        assertThat(person.skills).contains(Skill.Hard("Kotlin"))
    }

    @Test
    fun language() {
        val person = introduce {
            name("이인동")
            company("카카오")
            skills {
                soft("Android")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages).contains(Language("Korean", 5))
        assertThat(person.languages).contains(Language("English", 3))
    }
}
