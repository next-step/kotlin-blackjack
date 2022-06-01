package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @Test
    fun introduce() {
        val resume = introduce {
            name("이요한")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(resume.name).isEqualTo("이요한")
        assertThat(resume.company).isEqualTo("카카오")
        assertThat(resume.skills).containsExactly(
            Skill("A passion for problem solving", Skill.SkillType.SOFT),
            Skill("Good communication skills", Skill.SkillType.SOFT),
            Skill("Kotlin", Skill.SkillType.HARD),
        )
        assertThat(resume.languages).containsExactly(
            Language("Korean", 5),
            Language("English", 3),
        )
    }

    @ValueSource(strings = ["이요한", "yohan"])
    @ParameterizedTest
    fun name(value: String) {
        val resume = introduce {
            name(value)
        }

        assertThat(resume.name).isEqualTo(value)
    }

    @ValueSource(strings = ["kakao", "카카오"])
    @ParameterizedTest
    fun company(value: String) {
        val resume = introduce {
            name("이요한")
            company(value)
        }

        assertThat(resume.company).isEqualTo(value)
    }

    @Test
    fun skills() {
        val resume = introduce {
            name("이요한")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(resume.skills).containsExactly(
            Skill("A passion for problem solving", Skill.SkillType.SOFT),
            Skill("Good communication skills", Skill.SkillType.SOFT),
            Skill("Kotlin", Skill.SkillType.HARD),
        )
    }

    @Test
    fun languages() {
        val resume = introduce {
            name("이요한")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(resume.languages).containsExactly(
            Language("Korean", 5),
            Language("English", 3),
        )
    }
}
