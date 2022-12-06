package dsl

class SkillsBuilder(
    private var softSkills: SoftSkills = SoftSkills(),
    private var hardSkills: HardSkills = HardSkills()
) {
    fun soft(value: String) {
        val newSoftSkills = softSkills.add(value)
        softSkills = newSoftSkills
    }

    fun hard(value: String) {
        val newHardSkills = hardSkills.add(value)
        hardSkills = newHardSkills
    }

    fun build(): Skills {
        return Skills(hardSkills, softSkills)
    }
}

class Skills(val hardSkills: HardSkills, val softSkills: SoftSkills)

class HardSkills(hardSkills: List<String> = emptyList()) {
    private val _hardSkills: MutableList<String> = hardSkills.toMutableList()
    val hardSkills: List<String>
        get() = _hardSkills.toList()

    fun add(name: String): HardSkills {
        _hardSkills.add(name)
        return HardSkills(_hardSkills.toList())
    }
}

class SoftSkills(softSkills: List<String> = emptyList()) {
    private val _softSkills: MutableList<String> = softSkills.toMutableList()
    val softSkills: List<String>
        get() = _softSkills.toList()

    fun add(name: String): SoftSkills {
        _softSkills.add(name)
        return SoftSkills(_softSkills.toList())
    }
}
