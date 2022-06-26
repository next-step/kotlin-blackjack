package dsl

data class Person(val name: String, val company: String, val skills: List<Skill>, val languages: Map<String, Int>) {}

data class Skill(val type: SkillType, val description: String) {
    companion object {
        fun soft(description: String): Skill {
            return Skill(SkillType.Soft, description)
        }

        fun hard(description: String): Skill {
            return Skill(SkillType.Hard, description)
        }
    }
}

sealed class SkillType {
    object Soft: SkillType()
    object Hard: SkillType()
}

class SkillsBuilder {
    private val skills: MutableList<Skill> = ArrayList()

    fun soft(description: String) {
        skills.add(Skill.soft(description))
    }

    fun hard(description: String) {
        skills.add(Skill.hard(description))
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
