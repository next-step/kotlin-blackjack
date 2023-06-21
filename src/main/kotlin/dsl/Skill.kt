package dsl

sealed class Skill(val description: String)

class Soft(description: String) : Skill(description)

class Hard(description: String) : Skill(description)
