package study

class SkillsBuilder {
    private var softSkills = mutableListOf<SoftSkill>()
    private var hardSkills = mutableListOf<HardSkill>()

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
    softSkills: SoftSkills,
    hardSkills: HardSkills,
) {
    val softSkills = softSkills.softSkills
    val hardSkills = hardSkills.hardSkills
}

class SoftSkills(softSkills: List<SoftSkill>) {
    val softSkills = softSkills.map { it.value }
}

class HardSkills(hardSkills: List<HardSkill>) {
    val hardSkills = hardSkills.map { it.value }
}

@JvmInline
value class SoftSkill(val value: String)

@JvmInline
value class HardSkill(val value: String)
