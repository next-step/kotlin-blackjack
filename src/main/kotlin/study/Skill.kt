package study

data class Skill(val type: Type, val description: String) {
    enum class Type {
        SOFT, HARD
    }

    companion object {
        fun soft(description: String) = Skill(Type.SOFT, description)
        fun hard(description: String) = Skill(Type.HARD, description)
    }
}
