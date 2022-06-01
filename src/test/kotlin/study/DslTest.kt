package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ValueSource(strings = ["백승열", "우디"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("백승열")
            company("카카오엔터프라이즈")
        }
        assertThat(person.name).isEqualTo("백승열")
        assertThat(person.company).isEqualTo("카카오엔터프라이즈")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("백승열")
            company("카카오엔터프라이즈")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills?.let {
            assertThat(Soft("A passion for problem solving") in it).isTrue
            assertThat(Soft("Good communication skills") in it).isTrue
            assertThat(Hard("Kotlin") in it).isTrue
        }
    }

    @Test
    fun languages() {
        val person = introduce {
            name("백승열")
            company("카카오엔터프라이즈")
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
        person.languages?.let {
            assertThat(Language("Korean", 5) in it).isTrue
            assertThat(Language("English", 3) in it).isTrue
        }
    }
}
