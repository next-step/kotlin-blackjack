package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun personDsl() {
        val actualPerson = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("a")
                soft("b")
                hard("c")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        val expectedPerson = Person(
            name = "홍길동",
            company = "활빈당",
            skills = Skills(
                softs = listOf("a", "b"),
                hards = listOf("c"),
            ),
            languages = Languages(
                levels = listOf(
                    Level("Korean", 5),
                    Level("English", 3),
                )
            )
        )

        assertThat(actualPerson).isEqualTo(expectedPerson)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
