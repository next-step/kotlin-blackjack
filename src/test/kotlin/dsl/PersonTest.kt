package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonTest {

    @Test
    fun nameTest() {
        // given, when
        val person = introduce {
            name("유인근")
        }

        // then
        assertThat(person.name).isEqualTo("유인근")
    }

    @Test
    fun companyTest() {
        // given, when
        val person = introduce {
            company("카카오")
        }

        // then
        assertThat(person.company).isEqualTo("카카오")
    }

    @Test
    fun skillsTest() {
        // given, when
        val person = introduce {
            skills {
                soft("Good communication skills")
                soft("Documentation")
                hard("Programming")
            }
        }

        // then
        val (skill1, skill2, skill3) = person.skills.skills

        assertThat(skill1).isEqualTo(Skill.Soft("Good communication skills"))
        assertThat(skill2).isEqualTo(Skill.Soft("Documentation"))
        assertThat(skill3).isEqualTo(Skill.Hard("Programming"))
    }

    @Test
    fun languagesTest() {
        // given, when
        val person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        // then
        val (language1, language2) = person.languages.languages

        assertThat(language1).isEqualTo(Language("Korean", 5))
        assertThat(language2).isEqualTo(Language("English", 3))
    }

    @Test
    fun introduceTest() {
        // given, when
        val person = introduce {
            name("유인근")
            company("카카오")
            skills {
                soft("Good communication skills")
                soft("Documentation")
                hard("Programming")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        // then
        assertThat(person.name).isEqualTo("유인근")
        assertThat(person.company).isEqualTo("카카오")

        val (skill1, skill2, skill3) = person.skills.skills

        assertThat(skill1).isEqualTo(Skill.Soft("Good communication skills"))
        assertThat(skill2).isEqualTo(Skill.Soft("Documentation"))
        assertThat(skill3).isEqualTo(Skill.Hard("Programming"))

        val (language1, language2) = person.languages.languages

        assertThat(language1).isEqualTo(Language("Korean", 5))
        assertThat(language2).isEqualTo(Language("English", 3))
    }

    private fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }
}
