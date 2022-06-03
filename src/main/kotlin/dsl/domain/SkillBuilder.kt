package dsl.domain

/**
 * Created by Jaesungchi on 2022.06.03..
 */
class SkillBuilder {
    private var softs: MutableList<String> = mutableListOf()
    private var hards: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softs.add(value)
    }

    fun hard(value: String) {
        hards.add(value)
    }

    fun build(): Skill {
        return Skill(softs, hards)
    }
}
