package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
/**
introduce {
    name("박재성")
    company("우아한형제들")
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
*/
*/
class DslTest {


    @ValueSource(strings = ["김기연", "사용자"])
    @ParameterizedTest
    fun `이름이 지정된다`(value: String) {
        // given
        // when
        val person = introduce {
            name(value)
        }
        // then
        assertThat(person.name).isEqualTo(value)
    }


    @Test
    internal fun `회사가 지정된다`() {
        // given
        // when
        val person = introduce {
            name("김기연")
            company("넥스트스텝")
        }

        // then
        assertThat(person.name).isEqualTo("김기연")
        assertThat(person.company).isEqualTo("넥스트스텝")
    }

    @Test
    internal fun `기술이 지정된다`() {
        // given
        // when
        val person = introduce {
            name("김기연")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        // then
        assertThat(person.name).isEqualTo("김기연")

        val skills = checkNotNull(person.skills) { "skill is not null" }
        assertAll({
            assertThat(skills.hards)
                .hasSize(1)
                .containsExactly("Kotlin")
        }, {
            assertThat(skills.softs)
                .hasSize(2)
                .containsExactly("A passion for problem solving", "Good communication skills")
        })

    }


}


fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
