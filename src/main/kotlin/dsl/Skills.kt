package dsl

data class Skills(
    val soft: List<Skill>,
    val hard: List<Skill>
)

data class Skill(
    val title: String
)
