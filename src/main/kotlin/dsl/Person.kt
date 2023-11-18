package dsl

class Person {
    lateinit var name: String
    lateinit var company: String
    lateinit var skills: List<Skill>
}

fun introduce(block: (Person) -> Unit): Person {
    val person = Person()
    block(person)
    return person
}
