package study

@JvmInline
value class Skills(val value: List<Skill>) {

    operator fun get(index: Int): Skill {
        return value[index]
    }

    class Builder {

        private var skills = mutableListOf<Skill>()

        fun soft(name: String) {
            skills.add(Skill.soft(name))
        }

        fun hard(name: String) {
            skills.add(Skill.hard(name))
        }

        fun build(): Skills {
            return Skills(skills.toList())
        }
    }
}

fun Skills?.orEmpty() = this ?: Skills(emptyList())
