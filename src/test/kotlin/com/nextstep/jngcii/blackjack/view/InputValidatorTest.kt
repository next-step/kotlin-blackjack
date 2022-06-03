package com.nextstep.jngcii.blackjack.view

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class InputValidatorTest {
    @Test
    fun `이름 입력값이 null일 수 없음`() {
        val names = InputValidator.parseNames(null)

        assertThat(names).isNull()
    }
}
