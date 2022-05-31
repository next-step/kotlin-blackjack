package step1.skill

data class Skills(
    val values: List<Skill> = emptyList()
)

data class Skill(
    val level: Level,
    val description: String
)

enum class Level {
    SOFT, HARD
}
