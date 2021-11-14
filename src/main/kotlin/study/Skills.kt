package study

class Skills(skills: List<Skill> = emptyList()) {

    private val skills = skills.toMutableList()

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    operator fun contains(skill: Skill): Boolean = skills.contains(skill)

    companion object {
        val EMPTY = Skills()
    }
}
