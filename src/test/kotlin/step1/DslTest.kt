package step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ParameterizedTest
    @ValueSource(strings = ["harris", "summer"])
    fun `introduce dsl로 객체 생성시 이름 필드 확인`(value: String) {
        //given & when
        val person = introduce {
            name(value)
        }
        //then
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun `introduce dsl로 객체 생성시 이름, 회사 필드 확인`() {
        //given && when
        val person = introduce {
            name("harris")
            company("ks")
        }
        //then
        assertAll(
            { assertThat(person.name).isEqualTo("harris") },
            { assertThat(person.company).isEqualTo("ks") }
        )
    }

    @Test
    fun `introduce dsl로 객체 생성시 skill 필드 확인`() {
        //given && when
        val person = introduce {
            name("harris")
            company("ks")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        //then
        assertAll(
            { assertThat(person.name).isEqualTo("harris") },
            { assertThat(person.company).isEqualTo("ks") },
            { assertThat(person.skills!!.soft[0]).isEqualTo("A passion for problem solving") },
            { assertThat(person.skills!!.soft[1]).isEqualTo("Good communication skills") },
            { assertThat(person.skills!!.hard[0]).isEqualTo("Kotlin") },
        )
    }

    @Test
    fun `introduce dsl로 객체 생성시 language 필드 확인`() {
        //given && when
        val person = introduce {
            name("harris")
            company("ks")
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
        //then
        assertAll(
            { assertThat(person.name).isEqualTo("harris") },
            { assertThat(person.company).isEqualTo("ks") },
            { assertThat(person.skills!!.soft[0]).isEqualTo("A passion for problem solving") },
            { assertThat(person.skills!!.soft[1]).isEqualTo("Good communication skills") },
            { assertThat(person.skills!!.hard[0]).isEqualTo("Kotlin") },
            { assertThat(person.languages!!.value.size).isEqualTo(2) },
            { assertThat(person.languages!!.value[0]).isEqualTo(Language("Korean", 5)) },
            { assertThat(person.languages!!.value[1]).isEqualTo(Language("English", 3)) },
        )
    }
}
