package dsl.skill

sealed class Skill(val title: String)

class SoftSkill(title: String) : Skill(title)
class HardSkill(title: String) : Skill(title)
