package study

class Skills {
    private val skills: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    override fun equals(other: Any?): Boolean {
        return other != null && this.javaClass == other.javaClass
    }

    override fun hashCode(): Int {
        return skills.hashCode()
    }
}
