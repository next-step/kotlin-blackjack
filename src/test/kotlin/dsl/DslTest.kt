package dsl

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.assertj.core.api.MapAssert
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["qyu", "gilssung"])
    fun introduce(value: String) {
        val resume: Resume = introduce {
            name(value)
        }

        assertThat(resume.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val resume: Resume = introduce {
            name("qyu")
            company("kakao")
        }

        assertThat(resume.name).isEqualTo("qyu")
        assertThat(resume.company).isEqualTo("kakao")
    }

    @Test
    fun skills() {
        val resume: Resume = introduce {
            name("qyu")
            company("kakao")
            skills {
                soft("Problem Solving")
                soft("Communication")
                hard("Kotlin")
            }
        }

        assertThat(resume.name).isEqualTo("qyu")
        assertThat(resume.company).isEqualTo("kakao")
        assertThat(resume.skills).extracting("soft").isEqualTo(listOf("Problem Solving", "Communication"))
        assertThat(resume.skills).extracting("hard").isEqualTo(listOf("Kotlin"))
    }

    @Test
    fun language() {
        val resume: Resume = introduce {
            name("qyu")
            company("kakao")
            skills {
                soft("Problem Solving")
                soft("Communication")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(resume.name).isEqualTo("qyu")
        assertThat(resume.company).isEqualTo("kakao")
        assertThat(resume.skills).extracting("soft").isEqualTo(listOf("Problem Solving", "Communication"))
        assertThat(resume.skills).extracting("hard").isEqualTo(listOf("Kotlin"))
        MapAssert(resume.languages?.list).contains(entry("Korean", 5), entry("English", 3))
    }
}
