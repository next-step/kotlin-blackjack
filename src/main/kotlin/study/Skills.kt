package study

class Skills private constructor(val soft: List<String>, val hard: List<String>) {
    class Builder {
        private val soft = mutableListOf<String>()
        private val hard = mutableListOf<String>()

        fun soft(skill: String) {
            this.soft.add(skill)
        }

        fun hard(skill: String) {
            this.hard.add(skill)
        }

        fun build(): Skills {
            return Skills(soft, hard)
        }
    }
}
