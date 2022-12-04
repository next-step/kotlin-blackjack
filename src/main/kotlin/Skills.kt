typealias Skill = Pair<Skills.Level, String>

data class Skills(
    val skills: List<Skill>,
) {
    enum class Level {
        SOFT, HARD
    }

    class Builder {
        private val skills = mutableListOf<Skill>()

        fun soft(description: String) {
            skills.add(Level.SOFT to description)
        }

        fun hard(description: String) {
            skills.add(Level.HARD to description)
        }

        fun build() = Skills(skills)
    }
}