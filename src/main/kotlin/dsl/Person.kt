package dsl

class PersonBuilder {
    lateinit var name: String
    var company: String = ""
    val softSkills: MutableList<String> = mutableListOf()
    val hardSkills: MutableList<String> = mutableListOf()
    val languages: MutableMap<String, Int> = mutableMapOf()

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

    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun build(): Person {
        return Person(name, company, softSkills, hardSkills, languages)
    }
}

class Person(
    val name: String,
    val company: String = "",
    val softSkills: List<String> = listOf(),
    val hardSkills: List<String> = listOf(),
    val languages: Map<String, Int> = mapOf(),
)
