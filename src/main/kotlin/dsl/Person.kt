package dsl

class PersonBuilder {
    lateinit var name: String
    var company: String = ""
    val softSkills: MutableList<String> = mutableListOf()
    val hardSkills: MutableList<String> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Person {
        return Person(name, company, softSkills, hardSkills)
    }
}

class Person(
    val name: String,
    val company: String = "",
    val softSkills: List<String> = listOf(),
    val hardSkills: List<String> = listOf(),
)
