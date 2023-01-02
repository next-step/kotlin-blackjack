package study.person

@JvmInline
value class Skills(
    val skills: MutableList<Skill> = mutableListOf(),
) {

    fun soft(name: String) {
        skills.add(Skill(name, Level.SOFT))
    }

    fun hard(name: String) {
        skills.add(Skill(name, Level.HARD))
    }
}
