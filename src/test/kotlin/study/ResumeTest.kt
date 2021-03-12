package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person: Person = introduce {
            name("HyeJin")
        }
        assertThat(person.name).isEqualTo("HyeJin")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            company("Naver Webtoon")
        }
        assertThat(person.company).isEqualTo("Naver Webtoon")
    }

    @Test
    fun hardSkill() {
        val person: Person = introduce {
            skills {
                hard("kotlin")
                soft("Java")
                soft("GraphQL")
            }
        }
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("kotlin"),
            Soft("Java"),
            Soft("GraphQL")
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            languages {
                "Korean" level 5
                "English" level 5
            }
        }
        assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 5)
        )
    }

    @Test
    fun resume() {
        val person: Person = introduce {
            name("HyeJin")
            company("Naver Webtoon")
            skills {
                hard("kotlin")
                soft("Java")
                soft("GraphQL")
            }
            languages {
                "Korean" level 5
                "English" level 5
            }
        }
        assertThat(person.name).isEqualTo("HyeJin")
        assertThat(person.company).isEqualTo("Naver Webtoon")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("kotlin"),
            Soft("Java"),
            Soft("GraphQL")
        )
        assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 5)
        )
    }
}

fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply(initializer)
//    return Person().apply {
//        this.initializer()
//    }
}
