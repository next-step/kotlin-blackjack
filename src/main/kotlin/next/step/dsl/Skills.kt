package next.step.dsl


data class Skills(val soft: Set<String> = emptySet(), val hard: Set<String> = emptySet())

class SkillsBuilder() {
    private var soft: MutableSet<String> = mutableSetOf()
    private var hard: MutableSet<String> = mutableSetOf()

    fun soft(skill: String) {
        soft.add(skill)
    }

    fun hard(skill: String) {
        hard.add(skill)
    }

    fun build(): Skills = Skills(soft, hard)

}

fun skills(block: SkillsBuilder.() -> Unit): Skills = SkillsBuilder().apply(block).build()
