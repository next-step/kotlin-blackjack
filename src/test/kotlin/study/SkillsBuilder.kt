package study

class SkillsBuilder {
    private val _softSkillList = mutableListOf<String>()
    val softSkillList: List<String>
        get() = _softSkillList

    private val _hardSkillList = mutableListOf<String>()
    val hardSkillList: List<String>
        get() = _hardSkillList

    fun softSkills(valueList: List<String>) {
        valueList.forEach { softSkills(it) }
    }

    fun softSkills(value: String) {
        _softSkillList.add(value)
    }

    fun hardSkills(valueList: List<String>) {
        valueList.forEach { hardSkills(it) }
    }

    fun hardSkills(value: String) {
        _hardSkillList.add(value)
    }

    fun build(): Skills {
        return Skills(_softSkillList, _hardSkillList)
    }
}
