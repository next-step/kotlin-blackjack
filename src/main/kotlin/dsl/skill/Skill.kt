package dsl.skill

data class Skill(var skillType: SkillType, var description: String)

fun List<Skill>.deepCopy() = this.map { it.copy() }
