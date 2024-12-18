package dsl.model

class Skills(skills: List<Skill>) {
    val skills = skills.toList()
        get() = field.toList()
}
