package dsl

class Skills {
    private val soft: MutableList<Skill> = mutableListOf()
    private val hard: MutableList<Skill> = mutableListOf()

    fun skills(): MutableList<Skill> {
        val collection = mutableListOf<Skill>()
        collection.addAll(soft.toList())
        collection.addAll(hard.toList())
        return collection
    }

    fun soft(name: String) {
        this.soft.add(Skill.Soft(name))
    }

    fun hard(name: String) {
        this.hard.add(Skill.Hard(name))
    }
}
