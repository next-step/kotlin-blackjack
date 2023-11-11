package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person =
    PersonBuilder()
        .apply(block)
        .build()

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skillList: List<Skill>

    inner class SkillListBuilder {
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

    fun skills(block: SkillListBuilder.() -> Unit) {
        skillList = SkillListBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skillList)
    }
}
