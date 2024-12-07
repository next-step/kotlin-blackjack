package dsl.model

data class Skill(
    val type: Type,
    val description: String,
) {
    enum class Type {
        SOFT,
        HARD,
    }
}
