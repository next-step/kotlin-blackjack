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
        assertEquals(person.skills[0], Skill.Soft("Good communication skills"))
        assertEquals(person.skills[1], Skill.Soft("Documentation"))
        assertEquals(person.skills[2], Skill.Hard("Programming"))
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
        assertEquals(person.languages[0], Language("Korean", 5))
        assertEquals(person.languages[1], Language("English", 3))
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

        assertEquals(person.skills[0], Skill.Soft("Good communication skills"))
        assertEquals(person.skills[1], Skill.Soft("Documentation"))
        assertEquals(person.skills[2], Skill.Hard("Programming"))

        assertEquals(person.languages[0], Language("Korean", 5))
        assertEquals(person.languages[1], Language("English", 3))
    }

    private fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }
}
