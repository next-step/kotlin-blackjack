package dsl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PersonTest {

    @Test
    fun nameTest() {
        val person = introduce {
            name("유인근")
        }

        assertEquals(person.name, "유인근")
    }

    @Test
    fun companyTest() {
        val person = introduce {
            name("유인근")
            company("카카오")
        }

        assertEquals(person.name, "유인근")
        assertEquals(person.company, "카카오")
    }

    @Test
    fun skillsTest() {
        val person = introduce {
            name("유인근")
            company("카카오")
            skills {
                soft("Good communication skills")
                soft("Documentation")
                hard("Programming")
            }
        }

        assertEquals(person.name, "유인근")
        assertEquals(person.company, "카카오")
        assertEquals(person.skills[0], Skill.Soft("Good communication skills"))
        assertEquals(person.skills[1], Skill.Soft("Documentation"))
        assertEquals(person.skills[2], Skill.Hard("Programming"))
    }

    private fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }
}
