package com.nextstep.jngcii.dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class HardSkillTest {
    @ParameterizedTest
    @ValueSource(strings = ["kotlin", "Kotlin", "KOTLIN"])
    fun kotlinEnumFindTest(value: String) {
        assertThat(HardSkill.of(value)).isEqualTo(HardSkill.KOTLIN)
    }

    @ParameterizedTest
    @ValueSource(strings = ["kolin", "java", "python"])
    fun hardSkillNullTest(value: String) {
        assertThat(HardSkill.of(value)).isNull()
    }
}
