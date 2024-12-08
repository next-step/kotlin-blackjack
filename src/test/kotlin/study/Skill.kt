package study

sealed interface Skill {
    val description: String
}

data class SoftSkill(override val description: String): Skill

data class HardSkill(override val description: String): Skill

open class SuperHardSkill(override val description: String): Skill

class SuperSuperHardSkill(description: String) : SuperHardSkill(description)
