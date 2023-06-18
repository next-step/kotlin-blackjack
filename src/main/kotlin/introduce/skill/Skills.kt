package introduce.skill

import introduce.IntroduceMaker
import introduce.skill.Skill.Companion.hardSkill
import introduce.skill.Skill.Companion.softSkill

@JvmInline
value class Skills(
    val values: List<Skill> = listOf(),
)

@IntroduceMaker
class SkillsBuilder {
    private val values: MutableList<Skill> = mutableListOf()

    fun soft(information: String) = values.add(softSkill(information))

    fun hard(information: String) = values.add(hardSkill(information))

    fun build(): Skills = Skills(values.toList())
}
