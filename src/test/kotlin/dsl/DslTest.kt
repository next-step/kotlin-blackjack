package dsl

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class DslTest {

    @ParameterizedTest
    @ValueSource(strings = ["최윤호", "씨유", "제이슨", "브라운", "포비"])
    fun `introduce 빌더 함수를 이용하여 이름을 만든다`(name: String) {
        // given
        val person = introduce {
            name(name)
        }

        // when
        val actual = person.name

        // then
        val expected = name
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["네이버", "카카오", "라인", "쿠팡", "우아한형제들"])
    fun `introduce 빌더 함수를 이용하여 회사에 취직한다`(company: String) {
        // given
        val person = introduce {
            name("최윤호")
            company(company)
        }

        // when
        val actual = person.company

        // then
        val expected = company
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `introduce 빌더 함수를 이용하여 기술을 배운다`() {
        // given
        val person = introduce {
            name("최윤호")
            skills {
                soft("A")
                hard("B")
            }
        }

        // when
        val actual = person.skills == listOf(
            Skill.SoftSkill("A"),
            Skill.HardSkill("B"),
        )

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `introduce 빌더 함수를 이용하여 언어를 배운다`() {
        // given
        val person = introduce {
            name("최윤호")
            languages {
                "java" level 1
                "kotlin" level 2
            }
        }

        // when
        val actual = person.language == listOf(
            Language("java", 1),
            Language("kotlin", 2),
        )

        // then
        assertThat(actual).isTrue
    }
}