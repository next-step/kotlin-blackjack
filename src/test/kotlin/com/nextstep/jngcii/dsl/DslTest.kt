package com.nextstep.jngcii.dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person = introduce {
            name("peter")
        }

        assertThat(person.name).isEqualTo("peter")
    }
}
