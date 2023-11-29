package dsl

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["양기현", "웨이드"])
    @ParameterizedTest
    fun name(value: String) {
        val person = introduce {
            name(value)
        }
        assertEquals(value, person.name)
    }

    @Test
    fun company() {
        val person = introduce {
            company("활빈당")
        }
        assertEquals("활빈당", person.company)
    }

    @Test
    fun skills() {
        val person = introduce {
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertEquals("A passion for problem solving", person.softSkills[0])
        assertEquals("Good communication skills", person.softSkills[1])
        assertEquals("Kotlin", person.hardSkills[0])
    }

    @Test
    fun languages() {
        val person = introduce {
            languages {
                "korean" level  1
                "english" level 2
            }
        }
        assertThat(person.languages).contains(
            entry("korean", 1),
            entry("english", 2)
        )
    }

    @Test
    fun introduce() {
        val person = introduce {
            name("양기현")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "korean" level  1
                "english" level 2
            }
        }
        assertEquals("양기현", person.name)
        assertEquals("활빈당", person.company)
        assertEquals("A passion for problem solving", person.softSkills[0])
        assertEquals("Good communication skills", person.softSkills[1])
        assertEquals("Kotlin", person.hardSkills[0])
        assertThat(person.languages).contains(
            entry("korean", 1),
            entry("english", 2)
        )
    }
}
