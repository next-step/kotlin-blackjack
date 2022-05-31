package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DslTest {
    @ParameterizedTest
    @CsvSource(
        "jade,kakao,Kotlin,Korean,5",
        "sehee,woowa,Java,Spanish,6",
    )
    internal fun introduce(name: String, company: String, skill: String, lang: String, level: Int) {
        val person: Person = introduce {
            name(name)
            company(company)
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard(skill)
            }
            languages {
                "Korean" level 5
                lang level level
            }
        }

        println(person)
        assertThat(person.name).isEqualTo(name)
        assertThat(person.company).isEqualTo(company)
        assertThat(person.skills.last().desc).isEqualTo(skill)
        assertThat(person.languages.last().name).isEqualTo(lang)
        assertThat(person.languages.last().level).isEqualTo(level)
    }
}
