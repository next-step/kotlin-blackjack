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

    @Test
    fun skill() {
        val person = introduce {
            name("peter")
            company("kakao")
            skills {
                soft("a passion for problem solving")
                soft("good communication skills")
                hard(Skill.Kotlin)
            }
        }

        val expected = listOf(
            Skill.Soft("a passion for problem solving"),
            Skill.Soft("good communication skills"),
            Skill.Kotlin
        )

        assertThat(person.skills).isEqualTo(expected)
    }

    @Test
    fun language() {
        val person = introduce {
            name("peter")
            company("kakao")
            skills {
                soft("a passion for problem solving")
                soft("good communication skills")
                hard(Skill.Kotlin)
            }
            languages {
                Language.Kind.KOREAN level Language.Level.FIVE
                Language.Kind.ENGLISH level Language.Level.THREE
            }
        }

        val expected = listOf(
            Language(Language.Kind.KOREAN, Language.Level.FIVE),
            Language(Language.Kind.ENGLISH, Language.Level.THREE)
        )

        assertThat(person.languages).isEqualTo(expected)
    }
}
