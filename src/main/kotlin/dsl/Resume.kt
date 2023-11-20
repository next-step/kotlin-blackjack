package dsl

data class Resume(
    val name: String,
    val company: String,
    val skills: Skill,
    val languages: Language
) {
    class Builder(
        private var name: String = "",
        private var company: String = "",
        private var skills: Skill.Builder = Skill.Builder(),
        private var languages: Language.Builder = Language.Builder()
    ) {

        fun name(name: String) {
            this.name = name
        }

        fun company(companyName: String) {
            this.company = companyName
        }

        fun skills(block: (@Dsl Skill.Builder).() -> Unit) {
            skills.block()
        }

        fun languages(block: (@Dsl Language.Builder).() -> Unit) {
            languages.block()
        }

        internal fun build(): Resume {
            return Resume(name, company, skills.build(), languages.build())
        }
    }
}

data class Skill(
    val softSkills: List<String>,
    val hardSkills: List<String>
) {
    class Builder(
        private val softSkills: MutableList<String> = mutableListOf(),
        private val hardSkills: MutableList<String> = mutableListOf()
    ) {

        fun soft(skill: String) {
            softSkills.add(skill)
        }

        fun hard(skill: String) {
            hardSkills.add(skill)
        }

        internal fun build(): Skill {
            return Skill(softSkills, hardSkills)
        }
    }
}

data class Language(
    val languages: Map<String, Int>
) {
    class Builder(
        private val languages: MutableMap<String, Int> = mutableMapOf()
    ) {

        infix fun String.level(level: Int) {
            languages[this] = level
        }

        internal fun build(): Language {
            return Language(languages)
        }
    }
}

fun introduce(block: (@Dsl Resume.Builder).() -> Unit): Resume {
    val builder = Resume.Builder()
    builder.block()
    return builder.build()
}
