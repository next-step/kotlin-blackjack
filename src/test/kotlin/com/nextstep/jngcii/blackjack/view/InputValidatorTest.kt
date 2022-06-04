package com.nextstep.jngcii.blackjack.view

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputValidatorTest {
    @Test
    fun `이름 입력값이 null일 수 없음`() {
        val names = InputValidator.parseNames(null)

        assertThat(names).isNull()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "1, 2, 3", "1, 2,", "1", "1, 2, 3, 4", ", 2, 3"
        ]
    )
    fun `이름 입력값이 1명 이하 혹은 3명 이상으로 파싱되면 null 반환`(input: String?) {
        val names = InputValidator.parseNames(input)

        assertThat(names).isNull()
    }

    @Test
    fun `이름 입력값이 2명인 경우 확인`() {
        val names = InputValidator.parseNames("1, 2")

        assertThat(names).isEqualTo(listOf("1", "2"))
    }

    @Test
    fun `이름이 Blank인 경우 확인`() {
        val names = InputValidator.parseNames(", 2")

        assertThat(names).isNull()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "y", "Y"
        ]
    )
    fun `카드 추가 여부가 y 이면 true 반환`(input: String?) {
        assertThat(InputValidator.parseMoreable(input)).isTrue
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "n", "N"
        ]
    )
    fun `카드 추가 여부가 n 이면 false 반환`(input: String?) {
        assertThat(InputValidator.parseMoreable(input)).isFalse
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "", " ", "a", "1"
        ]
    )
    fun `카드 추가 여부가 y, n 모두 아니면 null 반환`(input: String?) {
        assertThat(InputValidator.parseMoreable(input)).isNull()
    }
}
