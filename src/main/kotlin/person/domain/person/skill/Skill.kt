package person.domain.person.skill

sealed interface Skill {
    data class HardSkill(val name: String) : Skill

    data class SoftSkill(val name: String) : Skill
}
