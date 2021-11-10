package study.resume

class Skills(private val skills: MutableList<Skill> = mutableListOf()) : List<Skill> by skills {
    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun sealed(skill: Skill) =
        when (skill) {
            is Soft -> "Soft"
            is Hard -> "Hard"
        }
}
