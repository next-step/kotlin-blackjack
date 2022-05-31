package dsl

data class Skills(val soft: List<String>?, val hard: List<String>?)

class SkillsBuilder {
    private var soft: MutableList<String>? = null
    private var hard: MutableList<String>? = null

    fun soft(skill: String) {
        if (soft == null) soft = mutableListOf()
        soft?.add(skill)
    }

    fun hard(skill: String) {
        if (hard == null) hard = mutableListOf()
        hard?.add(skill)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}
