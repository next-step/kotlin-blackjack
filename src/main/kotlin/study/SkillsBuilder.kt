package study

class SkillsBuilder {
    private val softSkills = mutableListOf<SoftSkill>()
    private val hardSkills = mutableListOf<HardSkill>()

    fun soft(value: String) {
        softSkills += SoftSkill(value)
    }

    fun hard(value: String) {
        hardSkills += HardSkill(value)
    }

    fun build(): Skills {
        return Skills(
            SoftSkills(softSkills.toList()),
            HardSkills(hardSkills.toList()),
        )
    }
}

class Skills(
    softSkills: SoftSkills = SoftSkills(),
    hardSkills: HardSkills = HardSkills(),
) {
    val softSkills = softSkills.softSkills
    val hardSkills = hardSkills.hardSkills
}

class SoftSkills(softSkills: List<SoftSkill> = emptyList()) {
    val softSkills = softSkills.map { it.value }
}

class HardSkills(hardSkills: List<HardSkill> = emptyList()) {
    val hardSkills = hardSkills.map { it.value }
}

@JvmInline
value class SoftSkill(val value: String)

@JvmInline
value class HardSkill(val value: String)
