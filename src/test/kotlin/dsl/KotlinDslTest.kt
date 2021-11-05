package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class KotlinDslTest {

    @Test
    internal fun person() {
        val person = introduce {
            name("ninjasul")
            company("KakaoPay")
            skills {
                soft("communication")
                hard("kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.name).isEqualTo("ninjasul")
        assertThat(person.company).isEqualTo("KakaoPay")
        assertThat(person.skills.items).containsExactlyInAnyOrder(Soft("communication"), Hard("kotlin"))
        assertThat(person.languages.items).containsExactlyInAnyOrder(Language("Korean", 5), Language("English", 3))
    }

    @Test
    internal fun skills() {
        val skills = Person().skills {
            soft("communication")
            hard("kotlin")
        }

        assertThat(skills.items).containsExactlyInAnyOrder(Soft("communication"), Hard("kotlin"))
    }

    @Test
    internal fun languages() {
        val languages = Person().languages {
            "Korean" level 5
            "English" level 3
        }
        assertThat(languages.items).containsExactlyInAnyOrder(Language("Korean", 5), Language("English", 3))
    }
}
