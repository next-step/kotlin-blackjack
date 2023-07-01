package dsl
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

sealed class Skill {

    data class Soft(val name: String) : Skill()
    data class Hard(val name: String) : Skill()

    companion object {
        fun soft(name: String): Skill = Soft(name)
        fun hard(name: String): Skill = Hard(name)
    }
}
