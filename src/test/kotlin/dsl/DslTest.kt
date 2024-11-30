package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["홍길동", "철수"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person =
            introduce {
                name(value)
            }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("홍길동")
                company("우리회사")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                    "kotlin" to 1
                    "java" to 2
                }
            }

        assertAll(
            { assertThat(person.name).isEqualTo("홍길동") },
            { assertThat(person.company).isEqualTo("우리회사") },
            { assertThat(person.languages).isEqualTo(Languages(mapOf("kotlin" to 1, "java" to 2))) },
            {
                assertThat(
                    person.skills,
                ).isEqualTo(Skills(listOf("A passion for problem solving", "Good communication skills"), listOf("Kotlin")))
            },
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
