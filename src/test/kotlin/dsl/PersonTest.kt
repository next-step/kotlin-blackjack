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

    private fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }
}
