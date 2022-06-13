package dsl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PersonTest {

    @Test
    fun nameTest() {
        // given, when
        val person = introduce {
            name("유인근")
        }

        // then
        assertEquals(person.name, "유인근")
    }

    @Test
    fun companyTest() {
        // given, when
        val person = introduce {
            company("카카오")
        }

        // then
        assertEquals(person.company, "카카오")
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

        assertEquals(skill1, Skill.Soft("Good communication skills"))
        assertEquals(skill2, Skill.Soft("Documentation"))
        assertEquals(skill3, Skill.Hard("Programming"))
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

        assertEquals(language1, Language("Korean", 5))
        assertEquals(language2, Language("English", 3))
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
        assertEquals(person.name, "유인근")
        assertEquals(person.company, "카카오")

        val (skill1, skill2, skill3) = person.skills.skills

        assertEquals(skill1, Skill.Soft("Good communication skills"))
        assertEquals(skill2, Skill.Soft("Documentation"))
        assertEquals(skill3, Skill.Hard("Programming"))

        val (language1, language2) = person.languages.languages

        assertEquals(language1, Language("Korean", 5))
        assertEquals(language2, Language("English", 3))
    }

    private fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }
}
