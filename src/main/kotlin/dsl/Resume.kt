package dsl

data class Resume(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages
) {
    class Builder(
        private var name: String = "",
        private var company: String = "",
        private var skills: Skills.Builder = Skills.Builder(),
        private var languages: Languages.Builder = Languages.Builder()
    ) {

        fun name(name: String) {
            this.name = name
        }

        fun company(companyName: String) {
            this.company = companyName
        }

        fun skills(block: (@Dsl Skills.Builder).() -> Unit) {
            skills.block()
        }

        fun languages(block: (@Dsl Languages.Builder).() -> Unit) {
            languages.block()
        }

        internal fun build(): Resume {
            return Resume(name, company, skills.build(), languages.build())
        }
    }
}

data class Skills(
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

        internal fun build(): Skills {
            return Skills(softSkills, hardSkills)
        }
    }
}

data class Languages(
    val languages: Map<String, Int>
) {
    class Builder(
        private val languages: MutableMap<String, Int> = mutableMapOf()
    ) {

        infix fun String.level(level: Int) {
            languages[this] = level
        }

        internal fun build(): Languages {
            return Languages(languages)
        }
    }
}

fun introduce(block: (@Dsl Resume.Builder).() -> Unit): Resume {
    val builder = Resume.Builder()
    builder.block()
    return builder.build()
}
