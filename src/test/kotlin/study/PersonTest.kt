package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonTest {
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "김철수"])
    fun introduce(name: String) {
        val person = introduce {
            name(name)
        }

        assertThat(person.name).isEqualTo(name)
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
        }

        assertThat(person.name).isEqualTo("홍길동")
        assertThat(person.company).isEqualTo("활빈당")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skill?.softSkills).containsExactly("A passion for problem solving", "Good communication skills")
        assertThat(person.skill?.hardSkills).containsExactly("Kotlin")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
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

        assertThat(person.languages?.list?.first()?.name).isEqualTo("Korean")
        assertThat(person.languages?.list?.first()?.level).isEqualTo(5)
        assertThat(person.languages?.list?.last()?.name).isEqualTo("English")
        assertThat(person.languages?.list?.last()?.level).isEqualTo(3)
    }
}
