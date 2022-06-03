package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PersonTest {
    @ParameterizedTest
    @ValueSource(strings = ["김승갑", "justin"])
    fun `Person은 name 정보를 갖으며 DSL로 생성한다`(name: String) {
        val person: Person = introduce {
            name(name)
        }

        assertThat(person.name).isEqualTo(name)
    }

    @ParameterizedTest
    @ValueSource(strings = ["카카오", "kakao"])
    fun `Person은 company 정보를 갖으며 DSL로 생성한다`(company: String) {
        val person: Person = introduce {
            company(company)
        }

        assertThat(person.company).isEqualTo(company)
    }

    @Test
    fun `Person은 skills 정보를 갖으며 DSL로 생성한다`() {
        val person: Person = introduce {
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills.soft[0].title).isEqualTo("A passion for problem solving")
        assertThat(person.skills.soft[1].title).isEqualTo("Good communication skills")
        assertThat(person.skills.hard[0].title).isEqualTo("Kotlin")
    }
}
