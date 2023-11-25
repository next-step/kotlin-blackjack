package study.domain

class Skills {
    private val list = mutableListOf<Skill>()

    fun soft(description: String): Skills {
        list.add(Skill("Soft", description))
        return this
    }

    fun hard(description: String): Skills {
        list.add(Skill("Hard", description))
        return this
    }

    operator fun invoke() = list.toList()
}
