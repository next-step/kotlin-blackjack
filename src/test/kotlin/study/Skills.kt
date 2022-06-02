package study

data class Skills(val skills: List<Skill>)

sealed class Skill(val desc: String)

class Soft(value: String) : Skill(value)

class Hard(value: String) : Skill(value)
