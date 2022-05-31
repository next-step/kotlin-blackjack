package study

class Skills {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    override fun hashCode(): Int {
        return softSkills.size + hardSkills.size
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Skills) return false
        return softSkills == other.softSkills && hardSkills == other.hardSkills
    }
}
