package study

class SkillBuilder {
    private var soft: MutableList<String> = mutableListOf()
    private var hard: MutableList<String> = mutableListOf()

    fun soft(soft: String) {
        this.soft.add(soft)
    }

    fun hard(hard: String) {
        this.hard.add(hard)
    }

    fun build(): Skill {
        return Skill(soft, hard)
    }
}
