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

    @Test
    fun company() {
        val person = introduce {
            name("peter")
            company("kakao")
        }

        assertThat(person.company).isEqualTo("kakao")
    }

    @Test
    fun no_company() {
        val person = introduce {
            name("peter")
        }

        assertThat(person.company).isEqualTo("무직")
    }
}
