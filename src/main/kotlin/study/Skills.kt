package study

class SkillsBuilder {
    private var values: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        values.add(Soft(value))
    }

    fun hard(value: String) {
        values.add(Hard(value))
    }

    fun build(): Skills {
        return Skills(values)
    }
}

class Skills(val values: List<Skill>)
