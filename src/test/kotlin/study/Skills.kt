package study

class SkillsBuilder {
    private val softs = ArrayList<String>()
    private val hards = ArrayList<String>()

    fun soft(description: String) {
        softs.add(description)
    }

    fun hard(description: String) {
        hards.add(description)
    }

    fun build(): Skills {
        return Skills(softs.toList(), hards.toList())
    }
}

data class Skills(
    val softs: List<String>,
    val hards: List<String>,
)
