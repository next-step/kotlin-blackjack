package study

class LanguagesBuilder {
    private val levels = ArrayList<Level>()

    infix fun String.level(grade: Int) {
        val level = Level(this, grade)

        levels.add(level)
    }

    fun build(): Languages {
        return Languages(levels.toList())
    }
}

data class Languages(
    val levels: List<Level>
)

data class Level(
    val topic: String,
    val grade: Int,
)
