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
        val skillDesc = "A passion for problem solving"
        val language = "English"

        val person: Person = introduce {
            name(name)
            company(company)
            skills {
                soft(skillDesc)
                hard(skill)
            }
            languages {
                language level 5
                lang level level
            }
        }

        assertThat(person.name).isEqualTo(name)
        assertThat(person.company).isEqualTo(company)
        assertThat(person.company).contains(company)
        assertThat(person.skills.map { it.desc }).containsExactlyInAnyOrder(skill, skillDesc)
        assertThat(person.languages.map { it.name }).containsExactlyInAnyOrder(lang, language)
        assertThat(person.languages.map { it.level }).contains(level)
    }
}
