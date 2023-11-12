package kotlinDSL

class SkillsBuilder {
    private var softSkills = mutableListOf<Soft>()
    private var hardSkills = mutableListOf<Hard>()

    fun soft(desc: String) {
        softSkills.apply { add(Soft(desc)) }
    }

    fun hard(desc: String) {
        hardSkills.apply {
            add(Hard(desc))
        }
    }

    fun build(): Skills {
        return Skills(
            soft = softSkills,
            hard = hardSkills
        )
    }
}
