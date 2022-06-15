package study

import dsl.introduce
import dsl.language.Language
import dsl.person.Person
import dsl.skill.Hard
import dsl.skill.Soft
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["이상민", "박상민"])
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
            name("이상민")
            company("카카오")
        }
        assertThat(person.name).isEqualTo("이상민")
        assertThat(person.company).isEqualTo("카카오")
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("이상민")
            company("카카오")
            skills {
                soft("java")
                soft("kotlin")
                hard("ios")
            }
        }
        assertThat(person.skills.list).containsAnyOf(
            Soft("kotlin"),
            Soft("java"),
            Hard("ios")
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("이상민")
            company("카카오")
            skills {
                soft("java")
                soft("kotlin")
                hard("ios")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.languages.list).containsExactly(
            Language("Korean", 5),
            Language("English", 3),
        )
    }
}
