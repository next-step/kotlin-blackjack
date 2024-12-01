package study

interface Skill {
    val description: String
}

data class SoftSkill(override val description: String): Skill

data class HardSkill(override val description: String): Skill
