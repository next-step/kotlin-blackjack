package study

interface Skill {
    val description: String
}

data class Soft(override val description: String) : Skill
data class Hard(override val description: String) : Skill
