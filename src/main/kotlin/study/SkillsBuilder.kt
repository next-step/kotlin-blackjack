package study

class SkillsBuilder {
    private var softSkills: SoftSkills = SoftSkills(listOf())
    private var hardSkills: HardSkills = HardSkills(listOf())

    fun soft(value: String) {
        this.softSkills = softSkills.add(value)
    }

    fun hard(value: String) {
        this.hardSkills = hardSkills.add(value)
    }

    fun build(): Skills {
        return Skills(this.softSkills, this.hardSkills)
    }
}
