package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person =
    PersonBuilder()
        .apply(block)
        .build()

class PersonBuilder {
    private var name: String = ""
    private var company: String? = null
    private var skillList: List<Skill> = listOf()
    private var languages: Map<String, Int> = mapOf()

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

    inner class LanguageMapBuilder {
        private val langaugeMap: MutableMap<String, Int> = mutableMapOf()
        infix fun String.level(level: Int) {
            langaugeMap[this] = level
        }

        fun build() = langaugeMap.toMap()
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

    fun languages(block: LanguageMapBuilder.() -> Unit) {
        languages = LanguageMapBuilder().apply(block).build()
    }

    fun build(): Person {
        require(name.isNotEmpty()) { "Please initialize person's name." }
        return Person(name, company, skillList, languages)
    }
}
