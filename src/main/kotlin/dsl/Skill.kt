package dsl

interface Skill

@JvmInline
value class SoftSkill(val text: String) : Skill

@JvmInline
value class HardSkill(val text: String) : Skill

class SkillBuilder {
    val skills = arrayListOf<Skill>()

    fun soft(text: String) {
        skills.add(SoftSkill(text))
    }

    fun hard(text: String) {
        skills.add(HardSkill(text))
    }
}
