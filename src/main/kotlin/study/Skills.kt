package study

data class Skills(val softSkills: SoftSkills, val hardSkills: HardSkills)

@JvmInline
value class SoftSkills(val values: List<String>) {
    fun add(value: String): SoftSkills {
        return SoftSkills(this.values.plus(value))
    }
}

@JvmInline
value class HardSkills(val values: List<String>) {
    fun add(value: String): HardSkills {
        return HardSkills(this.values.plus(value))
    }
}
