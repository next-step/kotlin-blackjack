package dsl

data class Person(val name: String, val company: String, val skills: List<Skill>, val languages: Map<String, Int>) {}

data class Skill(val type: SkillType, val description: String) {}

enum class SkillType {
    SOFT,
    HARD
}

class SkillsBuilder {
    private var skills: List<Skill> = emptyList()

    fun soft(description: String) {
        val skillForAdd = skills.toMutableList()
        skillForAdd.add(Skill(SkillType.SOFT, description))
        skills = skillForAdd.toList()
    }

    fun hard(description: String) {
        val skillForAdd = skills.toMutableList()
        skillForAdd.add(Skill(SkillType.HARD, description))
        skills = skillForAdd.toList()
    }

    fun build(): List<Skill> {
        return skills
    }
}

class LanguagesBuilder {
    private var languages: Map<String, Int> = emptyMap()

    infix fun String.level(level: Int) {
        val languagesForAdd = languages.toMutableMap()
        languagesForAdd[this] = level
        languages = languagesForAdd.toMap()
    }

    fun build(): Map<String, Int> {
        return languages
    }
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: List<Skill>
    private lateinit var languages: Map<String, Int>

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
