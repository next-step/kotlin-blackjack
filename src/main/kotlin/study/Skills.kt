package study

class SkillBuilder {
    private var softs: MutableList<String> = mutableListOf()
    private var hards: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softs.add(value)
    }

    fun hard(value: String) {
        hards.add(value)
    }

    fun build(): Skills {
        return Skills(softs, hards)
    }
}

data class Skills(val softs: List<String>, val hards: List<String>)
