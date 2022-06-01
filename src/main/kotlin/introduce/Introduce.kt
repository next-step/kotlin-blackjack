package introduce

fun introduce(block: Introduce.() -> Unit): Person = Introduce().apply(block).build()

class Introduce() {

    lateinit var name: String
    var company: String? = null
    private val skills: Skills = Skills()
    private val languages: Languages = Languages()

    fun skills(block: Skills.() -> Unit) {
        skills.apply(block)
    }

    fun languages(block: Languages.() -> Unit) {
        languages.apply(block)
    }

    fun build() = Person(name, company, skills.value, languages.value)
}

class Skills {

    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()
    val value get() = mapOf("soft" to soft.map { it }, "hard" to hard.map { it })

    fun soft(skill: String) {
        soft.add(skill)
    }

    fun hard(skill: String) {
        hard.add(skill)
    }
}

class Languages {

    private val _value = mutableListOf<Pair<String, Int>>()
    val value get() = _value.associate { it }

    infix fun String.level(level: Int) {
        _value.add(this to level)
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Map<String, List<String>>,
    val languages: Map<String, Int>
)
