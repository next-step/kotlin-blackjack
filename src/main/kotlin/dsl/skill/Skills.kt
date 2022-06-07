package dsl.skill

data class Skills(val soft: MutableList<SoftSkill>, val hard: MutableList<HardSkill>) {
    fun getSoft(index: Int): SoftSkill {
        return soft[index]
    }

    fun getHard(index: Int): HardSkill {
        return hard[index]
    }
}
