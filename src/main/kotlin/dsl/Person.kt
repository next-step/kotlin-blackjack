package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person =
    PersonBuilder()
        .apply(block)
        .build()

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skillList: List<Skill>

    inner class SkillBuilder {
        private val skillList: MutableList<Skill> = mutableListOf()
        fun soft(name: String) {
            skillList.add(Skill(name, SkillType.SOFT))
        }

        fun hard(name: String) {
            skillList.add(Skill(name, SkillType.HARD))
        }

        fun build() = skillList.toList()
    }

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skillList = SkillBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skillList)
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>
)

data class Skill(val name: String, val type: SkillType)

enum class SkillType {
    SOFT,
    HARD
}
