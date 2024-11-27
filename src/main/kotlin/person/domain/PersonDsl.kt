package person.domain

import person.domain.person.Person
import person.domain.person.PersonBuilder

object PersonDsl {
    fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }
}
