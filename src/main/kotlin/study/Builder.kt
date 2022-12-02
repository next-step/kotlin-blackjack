package study

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills(emptyList(), emptyList())
    private var languages: Languages = Languages(emptyList())

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private var softs: List<String> = emptyList()
    private var hards: List<String> = emptyList()

    fun soft(value: String) {
        val mutableList = softs.toMutableList()
        mutableList.add(value)
        softs = mutableList
    }

    fun hard(value: String) {
        val mutableList = hards.toMutableList()
        mutableList.add(value)
        hards = mutableList
    }

    fun build(): Skills {
        return Skills(softs, hards)
    }
}

class LanguagesBuilder {
    private var levels: List<Level> = emptyList()

    infix fun String.level(level: Int) {
        val mutableList = levels.toMutableList()
        mutableList.add(Level(this, level))
        levels = mutableList
    }

    fun build(): Languages {
        return Languages(levels)
    }
}
