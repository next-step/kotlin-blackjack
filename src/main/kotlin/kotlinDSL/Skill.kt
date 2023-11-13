package kotlinDSL

sealed class Skill

data class Soft(val desc: String) : Skill()
data class Hard(val desc: String) : Skill()
