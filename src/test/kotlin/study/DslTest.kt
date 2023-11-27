package study

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    //    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "허균"])
    fun name(name: String) {
        // given, when
        val person = introduce {
            name(name)
        }

        // then
        assertEquals(name, person.name)
    }

    //    @ParameterizedTest
    @CsvSource("홍길동,활빈당")
    fun company(name: String, company: String) {
        // given, when
        val person = introduce {
            name(name)
            company(company)
        }

        // then
        assertEquals(name, person.name)
        assertEquals(company, person.company)
    }

    //    @Test
    fun skills() {
        // given
        val name = "홍길동"
        val company = "활빈당"
        val softSkill1 = "A passion for problem solving"
        val softSkill2 = "Good communication skills"
        val hardSkill1 = "Kotlin"

        // when
        val person = introduce {
            name(name)
            company(company)
            skills {
                soft(softSkill1)
                soft(softSkill2)
                hard(hardSkill1)
            }
        }

        // then
        assertEquals(name, person.name)
        assertEquals(company, person.company)
        assertEquals(softSkill1, person.skills.soft[0])
        assertEquals(softSkill2, person.skills.soft[1])
        assertEquals(hardSkill1, person.skills.hard[0])
    }

    @Test
    fun languages() {
        // given
        val name = "홍길동"
        val company = "활빈당"
        val softSkill1 = "A passion for problem solving"
        val softSkill2 = "Good communication skills"
        val hardSkill1 = "Kotlin"
        val language1 = "Korean" to 5
        val language2 = "English" to 3

        // when
        val person = introduce {
            name(name)
            company(company)
            skills {
                soft(softSkill1)
                soft(softSkill2)
                hard(hardSkill1)
            }
            languages {
                language1.first level language1.second
                language2.first level language2.second
            }
        }

        // then
        assertEquals(name, person.name)
        assertEquals(company, person.company)
        assertEquals(softSkill1, person.skills.soft[0])
        assertEquals(softSkill2, person.skills.soft[1])
        assertEquals(hardSkill1, person.skills.hard[0])
        assertEquals(hardSkill1, person.skills.hard[0])
        assertEquals(hardSkill1, person.skills.hard[0])
        assertEquals(language1.second, person.languages.languages[language1.first])
        assertEquals(language2.second, person.languages.languages[language2.first])
    }
}
