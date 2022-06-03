package dsl

data class Skills(
    val soft: List<Skill> = emptyList(),
    val hard: List<Skill> = emptyList()
)

data class Skill(val title: String)
